// Решено

package ua.step.puzzle;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * https://www.youtube.com/watch?v=6bN1HcRhse4
 *
 */
public class AnnaKorenina
{
    public static void main(String[] args)
    {
        List<String> list = Arrays.asList("Вронский", null, "Анна");
        Comparator<String> comp = Comparator.nullsLast(Comparator.naturalOrder());
        
        
        System.out.println(Collections.max(list, comp));
        
        /** Если собрать вместе ту информацию, которую удалось почерпнуть частично из видео, частично из интернета:
         * 1. При создании пота коллекция модифицируется в поток
         * 2. После этого мы можем получить поток в виде коллекции, используя метода collect класса Stream
         * 3. Метод maxBy ищет по полученной из потока коллекции максимальный элемент И возвращет его в виде результата,
         * обёрнутого в объект Optional - для того, чтобы в случае передачи потоком значения null не вызвать NullPointerException
         * 4. Далее у результата мы вызываем get(), согласно документации Oracle:
         * 				"If a value is present in this Optional, returns the value, otherwise throws NoSuchElementException."
         * Оттуда и получается NoSuchElementException.
         * 5. Чтобы избежать ошибки решено допилить код вызовом метода isPresent() перед вызовом метода get(). 
         * 
         * */
        System.out.println(list.stream().collect(Collectors.maxBy(comp)).isPresent() ? list.stream().collect(Collectors.maxBy(comp)).get() : null);

    }
}