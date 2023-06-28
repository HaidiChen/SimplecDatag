package personal.haidchen.aggregator;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.IntegerDeserializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;


@Configuration
public class KafkaConfig {

    @Value("${simplec.kafka.topic.name}")
    private String kafkaTopicName;

    @Value("${simplec.kafka.bootstrap.servers}")
    private String kafkaBrokers;

    @Autowired
    private BlablaMessageListener blablaMessageListener;

    @Bean
    public ConsumerFactory<Integer, byte[]> consumerFactory() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBrokers);
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ByteArrayDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(configs);
    }

    @Bean
    public ContainerProperties containerProperties() {
        ContainerProperties props = new ContainerProperties(kafkaTopicName);
        props.setGroupId("blablasConsumer");
        props.setMessageListener(blablaMessageListener);

        return props;
    }

    @Bean
    public KafkaMessageListenerContainer<Integer, byte[]> messageListenerContainer() {
        return new KafkaMessageListenerContainer(consumerFactory(), containerProperties());
    }
}
