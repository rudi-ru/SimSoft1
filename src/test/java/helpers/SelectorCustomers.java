package helpers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// Выбираем из списка имена и вычисляем среднее арифметическое длин имён, после возвращаем список неугодных
public class SelectorCustomers {

    public static List<String> customersRemover(String list){
        List<String> names = takeNames(list);

        // Считаем среднее арифметическое
        long totalLettersCount = names.stream().flatMapToInt(String::chars).count();
        long arithmeticMean = totalLettersCount / names.size();

        // Собираем в список имена для удаления из списка
        List<String> namesToDelete = names.stream().filter(n -> n.length() == arithmeticMean).toList();
    return  namesToDelete;
    }

    // Делим строку на части и берем первую часть (имя)
    public static List<String> takeNames(String list) {

        return Arrays.stream(list.split("\n"))
                .map(line -> line.split(" ")[0])
                .collect(Collectors.toList());
    }
}
