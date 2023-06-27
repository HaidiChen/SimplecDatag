package personal.haidchen.aggregator;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.IntegerDeserializer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;


@Configuration
public class KafkaConfig {

    private static final String TOPIC_NAME_BLABLA = "blablasFromSimplecWeb";

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ByteArrayDeserializer.class);

        return props;
    }

    @Bean
    public ConsumerFactory<Integer, byte[]> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    @Bean
    public MessageListener<Integer, byte[]> messageListener() {
        return new MessageListener<>() {

            @Override
            public void onMessage(ConsumerRecord<Integer, byte[]> data) {
                try (ByteArrayInputStream byteArrIn = new ByteArrayInputStream(data.value());
                        ObjectInputStream objIn = new ObjectInputStream(byteArrIn)) {
                    List<String> blablaLines = (List<String>) objIn.readObject();
                    blablaLines.forEach(System.out::println);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    @Bean
    public ContainerProperties containerProperties() {
        ContainerProperties props = new ContainerProperties(TOPIC_NAME_BLABLA);
        props.setGroupId("blablasConsumer");
        props.setMessageListener(messageListener());

        return props;
    }

    @Bean
    public KafkaMessageListenerContainer<Integer, byte[]> messageListenerContainer() {
        return new KafkaMessageListenerContainer(consumerFactory(), containerProperties());
    }
}
