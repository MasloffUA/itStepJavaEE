package ua.step.puzzle;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SagaOfExecutioner
{
    public void killAll(){
        ExecutorService ex = Executors.newSingleThreadExecutor();
        List<String> setnence = Arrays.asList("Казнить");
        ex.submit(()-> Files.write(Paths.get("Приговор.txt"), setnence));
        //ex.submit(()-> {Files.write(Paths.get("Приговор.txt"), setnence);});
    }
}
