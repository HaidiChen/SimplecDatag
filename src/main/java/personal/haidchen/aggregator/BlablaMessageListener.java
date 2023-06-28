package personal.haidchen.aggregator;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Component;


@Component
public class BlablaMessageListener implements MessageListener<Integer, byte[]> {

    private final BlablaRepository repo;

    public BlablaMessageListener(BlablaRepository repo) {
        this.repo = repo;
    }

    @Override
    public void onMessage(ConsumerRecord<Integer, byte[]> data) {
        try (ByteArrayInputStream byteArrIn = new ByteArrayInputStream(data.value());
                ObjectInputStream objIn = new ObjectInputStream(byteArrIn)) {
            List<String> blablaLines = (List<String>) objIn.readObject();
            Blabla freshBlabla = new Blabla();
            String blablaContent = blablaLines.stream().collect(Collectors.joining("\\n"));
            freshBlabla.setContent(blablaContent);
            repo.save(freshBlabla);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
