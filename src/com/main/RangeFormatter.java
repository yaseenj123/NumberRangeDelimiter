package com.main;

import java.util.*;

public class RangeFormatter implements NumberRangeSummarizer {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args)
    {
        NumberRangeSummarizer numberRangeSummarizer = new RangeFormatter();

        System.out.print("Enter your string of numbers: ");
        String input = sc.nextLine();

        String formatted = numberRangeSummarizer.summarizeCollection(numberRangeSummarizer.collect(input));

        System.out.println("Formatted string of numbers: " + formatted);
    }

    @Override
    public Collection<Integer> collect(String input)
    {
        String[] parts = input.split(",");
        List<Integer> numbers = new ArrayList<>();
        for (String part : parts)
        {
            try
            {
                int number = Integer.parseInt(part.trim());
                numbers.add(number);
            }
            catch (NumberFormatException nfe)
            {
                System.out.println("Exception: " + nfe);
            }
        }
        Collections.sort(numbers);
        return numbers;
    }

    @Override
    public String summarizeCollection(Collection<Integer> input)
    {
        List<String> ranges = new ArrayList<>();
        List<Integer> sortedInput = new ArrayList<>(input);
        Collections.sort(sortedInput);

        int rangeStart = sortedInput.get(0);
        int rangeEnd = rangeStart;

        for (int i = 1; i < sortedInput.size(); i++)
        {
            int current = sortedInput.get(i);
            if (current == rangeEnd + 1)
            {
                rangeEnd = current;
            }
            else
            {
                ranges.add(formatRange(rangeStart, rangeEnd));
                rangeStart = current;
                rangeEnd = current;
            }
        }
        ranges.add(formatRange(rangeStart, rangeEnd));
        return String.join(",", ranges);
    }

    public String formatRange(int start, int end)
    {
        if (start == end)
        {
            return Integer.toString(start);
        }
        else
        {
            return start + "-" + end;
        }
    }
}