/**
 * Task6 class
 *
 * @author Alexey Vladyko
 */

import util.TextProcessor;

public class Task6 {

    public static void main(String[] args) {

        String smallText = "Этто - проостой, несложный    текст. Его предназначение: тестирование функций Задания 6.\n" +
                "Текст состоит из (топот (палиндром))  нескольких предложений и абзацев!";

        String largeText = "квазары — это исключительно яркие     активные ядра галактик, в которых центральные черные дыры активно " +
                "поглощают вещество. Считается, что квазары представляют собой отдельный период эволюции крупных галактик, так как " +
                "большинство из них находится на космологических    расстояниях от Млечного Пути.\n" +
                "Одна из важнейших задач при изучении квазаров — определение вращения черных дыр, так как оно связано с " +
                "историей роста таких объектов, взаимодействием с окружающим веществом, размером последней устойчивой орбиты " +
                "и инициацией релятивистских струй, которые могут оказывать воздействие на всю родительскую галактику.\n" +
                "Падающее на черную дыру      вещество образует разогретый аккреционный диск и еще более раскаленное и разреженное гало " +
                "вокруг. рентгеновское излучение центров активных галактик характеризуется нетепловым спектром со степенной " +
                "зависимостью и хорошо объясняется моделью, где относительно холодный аккреционный диск является источником " +
                "ультрафиолетовых фотонов, которые в процессе обратного комптоновского рассеяния на очень горячих электронах в " +
                "гало приобретают дополнительную энергию.";

        String text = smallText;

        System.out.println("Исходный текст:\n" + text);
        TextProcessor textProcessor = new TextProcessor();
        long time;
        System.out.println("\n1. Во всех предложениях поменять местами первое и последнее слово:");
        time = System.nanoTime();
        System.out.println(textProcessor.swapFirstLastWords(text));
        System.out.printf("Время работы: %d ns%n", (System.nanoTime() - time));

        System.out.println("\n2. Удалить все слова из текста у которых длинна равна n (5):");
        time = System.nanoTime();
        System.out.println(textProcessor.deleteWordsWithLenghtN(text, 5));
        System.out.printf("Время работы: %d ns%n", (System.nanoTime() - time));

        System.out.println("\n3. Найти кол-во гласных и согласных букв в тексте:");
        time = System.nanoTime();
        textProcessor.countClasnyeAndSoglasnye(text);
        System.out.printf("Время работы: %d ns%n", (System.nanoTime() - time));

        System.out.println("\n4. Каждый n-й символ заменить заданным символом:");
        time = System.nanoTime();
        System.out.println(textProcessor.changeNSymbol(text, 5, '*'));
        System.out.printf("Время работы: %d ns%n", (System.nanoTime() - time));

        System.out.println("\n5. Найти кол-во повторений каждого слова в тексте:");
        time = System.nanoTime();
        System.out.println(textProcessor.countRepeatingWords(text));
        System.out.printf("Время работы: %d ns%n", (System.nanoTime() - time));

        System.out.println("\n6. Найти все слова максимальной и минимальной длинны:");
        time = System.nanoTime();
        System.out.println(textProcessor.findMinMaxWords(text));
        System.out.printf("Время работы: %d ns%n", (System.nanoTime() - time));

        System.out.println("\n7. Удаление лишних пробелов:");
        time = System.nanoTime();
        System.out.println(textProcessor.removeExtraSpaces(text));
        System.out.printf("Время работы: %d ns%n", (System.nanoTime() - time));

        System.out.println("\n8. Заменить рядом стоящие одинаковые символы на один символ:");
        time = System.nanoTime();
        System.out.println(textProcessor.cutIdenticalCharacters(text));
        System.out.printf("Время работы: %d ns%n", (System.nanoTime() - time));

        System.out.println("\n9. Найти кол-во слов, которые начинаются и заканчиваются одинаковой буквой:");
        time = System.nanoTime();
        System.out.println(textProcessor.countStartEndSameLetterWords(text));
        System.out.printf("Время работы: %d ns%n", (System.nanoTime() - time));

        System.out.println("\n10. Форматирование текста (большой текст для наглядности):");
        time = System.nanoTime();
        System.out.println(textProcessor.formattedText(largeText));
        System.out.printf("Время работы: %d ns%n", (System.nanoTime() - time));

        System.out.println("\n11. Удалить из текста все что заключено в круглые скобки:");
        time = System.nanoTime();
        System.out.println(textProcessor.cutTextInBkt(text));
        System.out.printf("Время работы: %d ns%n", (System.nanoTime() - time));

        System.out.println("\n12. Найти в тексте слова палиндромы:");
        time = System.nanoTime();
        System.out.println(textProcessor.findPalindroms(text));
        System.out.printf("Время работы: %d ns%n", (System.nanoTime() - time));

        System.out.println("\n13. Вывести все слова текста в обратном порядке:");
        time = System.nanoTime();
        System.out.println(textProcessor.reverseWordsOfText(text));
        System.out.printf("Время работы: %d ns%n", (System.nanoTime() - time));
    }
}
