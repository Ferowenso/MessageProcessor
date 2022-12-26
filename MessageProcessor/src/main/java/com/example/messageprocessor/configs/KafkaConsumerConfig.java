package com.example.messageprocessor.configs;

import com.example.messageprocessor.dto.ReceiptDto;
import com.example.messageprocessor.dto.WeatherDto;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.MicrometerConsumerListener;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Autowired
    private final PrometheusMeterRegistry meterRegistry;

    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaServer;

    private String kafkaGroupId = "server.broadcast";

    public KafkaConsumerConfig(PrometheusMeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Bean
    public Map<String, Object> weatherConsumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, "60000");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaGroupId);
        return props;
    }

    @Bean
    public Map<String, Object> receiptConsumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaGroupId);
        props.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_committed");
        return props;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Long, WeatherDto> weatherDtoConcurrentKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Long, WeatherDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(weatherDtoConsumerFactory());
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Long, ReceiptDto> receiptDtoConcurrentKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Long, ReceiptDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(receiptDtoConsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<Long, WeatherDto> weatherDtoConsumerFactory() {
        DefaultKafkaConsumerFactory<Long, WeatherDto> consumerFactory = new DefaultKafkaConsumerFactory<>(weatherConsumerConfigs(), new LongDeserializer(), new JsonDeserializer<>(WeatherDto.class, false));
        consumerFactory.addListener(new MicrometerConsumerListener<>(meterRegistry));
        return consumerFactory;
    }

    @Bean
    public ConsumerFactory<Long, ReceiptDto> receiptDtoConsumerFactory() {
        DefaultKafkaConsumerFactory<Long, ReceiptDto> consumerFactory = new DefaultKafkaConsumerFactory<>(receiptConsumerConfigs(), new LongDeserializer(), new JsonDeserializer<>(ReceiptDto.class, false));
        consumerFactory.addListener(new MicrometerConsumerListener<>(meterRegistry));
        return  consumerFactory;
    }

}
