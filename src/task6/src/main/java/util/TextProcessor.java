package util; /**
 * util.TextProcessor class
 *
 * @author Alexey Vladyko
 */

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextProcessor {
    private String sentenceRegexp = "[A-Я][А-Яа-я0-9 ,\\-():—]+[.!?][\\s]?";
    private String glasnyeRegexp = "[аеёиоуыэюя]{1}";
    private String soglasnyeRegexp = "[бвгджзйклмнпрстфхцчшщьъ]{1}";
    private String wordRegexp = "[А-Яа-я]+";

    //1. Во всех предложениях поменять местами первое и последнее слово
    public String swapFirstLastWords(String text) {
        StringBuilder sb = new StringBuilder();
        Pattern pattern = Pattern.compile(sentenceRegexp);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String[] arr = matcher.group().split(" ");

            int lastIndex = arr.length - 1;
            int diffEnd = 1;
            //Если в конце предложения '\n', то вместе с ".!?" берем и его
            if (arr[lastIndex].charAt(arr[lastIndex].length() - 1) == '\n') {
                diffEnd = 2;
            }
            int diffStart = 0;

            //Перемещаем последнее слово в начало предложения, делаем первую букву toUpperCase
            sb.append(Character.toUpperCase(arr[lastIndex].charAt(0)))
                    .append(arr[lastIndex].substring(1, arr[lastIndex].length() - diffEnd));
            //проверка на запятую после первого слова
            if (arr[0].charAt(arr[0].length() - 1) == ',') {
                diffStart = 1;
                sb.append(arr[0].charAt(arr[0].length() - 1));
            }
            //Добавляем середину предложения
            for (int i = 1; i < arr.length - 1; i++) {
                sb.append(" ").append(arr[i]);
            }
            /*
            Перемещаем первое слово в конец предложения, делаем первую букву toLowerCase,
            добавляем знак в конце предложения
             */
            sb.append(" ").append(Character.toLowerCase(arr[0].charAt(0))).append(arr[0].substring(1, arr[0].length() - diffStart))
                    .append(arr[lastIndex].substring(arr[lastIndex].length() - diffEnd));
            if (diffEnd == 1) sb.append(' ');
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    //2. Удалить все слова из текста у которых длинна равна n
    public String deleteWordsWithLenghtN(String text, int n) {
        StringBuilder sb = new StringBuilder();
        //Список знаков - разделителей слов
        List<Character> endOfWord = Arrays.asList(' ', ',', '.', '?', '(', ')', '!', ':', '\n');
        //билдер для слов
        StringBuilder sbTemp = new StringBuilder();
        for (char ch : text.toCharArray()) {
            //если нашли символ-разделитель, то проверяем длинну накопленного слова
            //если не равна n, то до добавляем в результирующий билдер и очищаем билдер для слов иначе удаляем
            if (endOfWord.contains(ch)) {
                if (sbTemp.length() != n) {
                    sb.append(sbTemp);
                    sbTemp.setLength(0);
                } else {
                    sbTemp.setLength(0);
                }
                sb.append(ch);
            } else {
                sbTemp.append(ch);
            }
        }
        return sb.toString();
    }

    //3. Найти кол-во гласных и согласных букв в тексте.
    public void countClasnyeAndSoglasnye(String text) {
        int countGlasnye = 0;
        int countSoglasnye = 0;
        text = text.toLowerCase();
        Pattern pattern = Pattern.compile(glasnyeRegexp);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            countGlasnye++;
        }
        pattern = Pattern.compile(soglasnyeRegexp);
        matcher = pattern.matcher(text);
        while (matcher.find()) {
            countSoglasnye++;
        }
        System.out.printf("Количество гласных: %d%n" +
                "Количество согласных: %d%n", countGlasnye, countSoglasnye);
    }

    //4. Каждый n-й символ заменить заданным символом.
    public String changeNSymbol(String text, int n, char symbol) {
        StringBuilder sb = new StringBuilder();
        int textLenght = text.length();
        for (int i = n - 1; i < textLenght - 1; i += n) {
            sb.append(text.substring(i - n + 1, i)).append(symbol);
        }
        sb.append(text.substring(sb.length()));
        return sb.toString();
    }

    //5. Найти кол-во повторений каждого слова в тексте.
    public String countRepeatingWords(String text) {
        StringBuilder sb = new StringBuilder();
        //разбиваем текст на слова и переводим в нижний регистр
        Pattern pattern = Pattern.compile(wordRegexp);
        Matcher matcher = pattern.matcher(text.toLowerCase());
        //Map для слов (ключ) и количества повторений (значение)
        Map<String, Integer> map = new HashMap<>();
        while (matcher.find()) {
            String word = matcher.group();
            //смотрим, есть ли слово в мапе
            Integer count = map.get(word);
            if (count == null) {
                count = 0;
            }
            //увеличиваем счетчик и записываем в мапу
            map.put(word, ++count);
        }
        //перебираем мапу и собираем результат в билдере
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append('\n');
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    //6. Найти все слова максимальной и минимальной длинны.
    public String findMinMaxWords(String text) {
        StringBuilder sb = new StringBuilder();
        //билдер для слов минимальной длины
        StringBuilder sbMin = new StringBuilder();
        //билдер для слов максимальной длины
        StringBuilder sbMax = new StringBuilder();
        // min и max длина слов
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        //разбиваем текст на слова...
        Pattern pattern = Pattern.compile(wordRegexp);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String word = matcher.group();
            //если длинна слова равна минимальной, то добавляем его в билдер
            //если длина меньше, то сохраняем новую минимальную длинну,
            //очищаем билдер и записываем в него слово
            if (word.length() == min) {
                sbMin.append(word).append(": ").append(word.length()).append('\n');
            } else if (word.length() < min) {
                min = word.length();
                sbMin.setLength(0);
                sbMin.append(word).append(": ").append(word.length()).append('\n');
            }
            //тоже самое для максимальной длины
            if (word.length() == max) {
                sbMax.append(word).append(": ").append(word.length()).append('\n');
            } else if (word.length() > max) {
                max = word.length();
                sbMax.setLength(0);
                sbMax.append(word).append(": ").append(word.length()).append('\n');
            }
        }
        sb.append("Слова минимальной длинны:\n").append(sbMin).append("Слова максимальной длинны:\n").append(sbMax);
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    //7. Удаление лишних пробелов.
    public String removeExtraSpaces(String text) {
        StringBuilder sb = new StringBuilder();
        sb.append(text.charAt(0));
        //смещение при удалении повторяющихся пробелов
        int diff = 1;
        for (int i = 1; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch == ' ' && sb.charAt(i - diff) == ' ') {
                diff++;
                continue;
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    //8. Заменить рядом стоящие одинаковые символы на один символ.
    public String cutIdenticalCharacters(String text) {
        StringBuilder sb = new StringBuilder();
        sb.append(text.charAt(0));
        //смещение при удалении повторяющихся символов
        int diff = 1;
        for (int i = 1; i < text.length(); i++) {
            char ch = text.charAt(i);
            //если нашли повторяющий символ - увеличиваем смещение и не добавляем его
            if (sb.charAt(i - diff) == ch) {
                diff++;
                continue;
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    //9. Найти кол-во слов, которые начинаются и заканчиваются одинаковой буквой.
    public String countStartEndSameLetterWords(String text) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        Pattern pattern = Pattern.compile(wordRegexp);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String word = matcher.group();
            if (word.toLowerCase().charAt(0) == word.charAt(word.length() - 1)) {
                sb.append(word).append(" ");
                count++;
            }
        }
        sb.append("\nКоличество слов, которые начинаются и заканчиваются одинаковой буквой: ").append(count);
        return sb.toString();
    }

    /*
    10. Форматирование текста в следующем виде: так, чтобы все абзацы имели отступ ровно 4 пробела, каждое
    предложение начиналось с заглавной буквы, а длина каждой строки была не более 120 символов и не имела
    начальными и конечными символами пробельный символ.
     */
    public String formattedText(String text) {
        StringBuilder sb = new StringBuilder("    ");
        //счетчик для перевода первой буквы в предложении в верхнйи регистр
        int toUp = 0;
        //билдер для строк не более 120 символов
        StringBuilder sbString = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (toUp == 0) {
                ch = Character.toUpperCase(ch);
                toUp--;
            } else if (toUp > 0) {
                toUp--;
            }
            sbString.append(ch);

            //если конец абзаца, то сбрасываем строку и следующую начинаем с отступа
            if (ch == '\n') {
                sb.append(sbString);
                sbString.setLength(0);
                sbString.append("    ");
                toUp = 0;
                continue;
                //если конец предложения, то устанавливаем счетчик для увеличения первой буквы следущего предложения
            } else if (ch == '.' || ch == '!' || ch == '?') {
                toUp = 1;
            }
            //если строка достигла 120 символов, то ищем последнй пробел, сохраняем окончание строки,
            //делаем перевод строки и вставляем окончание на следующую строку
            if (sbString.length() == 120) {
                int lastSpaceIndex = sbString.lastIndexOf(" ");
                StringBuilder sbTemp = new StringBuilder(sbString.subSequence(lastSpaceIndex + 1, sbString.length()));
                sb.append(sbString.subSequence(0, lastSpaceIndex)).append('\n');
                sbString = sbTemp;
            }

        }
        sb.append(sbString);
        return sb.toString();
    }

    //11. Удалить из текста все что заключено в круглые скобки.
    public String cutTextInBkt(String text) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (char ch : text.toCharArray()) {
            //если нашли открывающую скобку - счетчик увеличиваем и, пока он больше нуля, в билдер не добавляем
            if (ch == '(') {
                count++;
                sb.append(ch);
                continue;
            } else if (ch == ')') {
                count--;
                sb.append(ch);
                continue;
            } else if (count == 0) {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    //12. Найти в тексте слова палиндромы
    public String findPalindroms(String text) {
        StringBuilder sb = new StringBuilder();
        Pattern pattern = Pattern.compile(wordRegexp);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String word = matcher.group();
            //если слово равно самому себе наоборот - то это палиндром
            //ну или попарно сравнить соответствующие символы сначала и с конца))
            if (word.equalsIgnoreCase(new StringBuilder(word).reverse().toString())) {
                sb.append(word).append('\n');
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    //13. Вывести все слова текста в обратном порядке
    public String reverseWordsOfText(String text) {
        StringBuilder sb = new StringBuilder();
        //Список знаков - разделителей слов
        List<Character> endOfWord = Arrays.asList(' ', ',', '.', '?', '(', ')', '!', ':', '\n');
        //билдер для аккумулирования слов
        StringBuilder sbTemp = new StringBuilder();
        for (int i = text.length() - 1; i >= 0; i--) {
            char ch = text.charAt(i);
            //если следующий символ - разделитель, то реверсим sbTemp и (возможно) получаем слово
            //иначе добавляем символ в sbTemp
            if (endOfWord.contains(ch)) {
                sb.append(sbTemp.reverse());
                sbTemp.setLength(0);
                //для более красивого отображения переворачиваем скобки
                if (ch == '(') {
                    ch = ')';
                } else if (ch == ')') ch = '(';
                sb.append(ch);
            } else {
                sbTemp.append(ch);
            }
        }
        sb.append(sbTemp.reverse());
        return sb.toString();
    }

}
