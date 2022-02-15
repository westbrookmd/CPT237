//*********************************************
//
// Assignment 3 Helper Class
//
// Author: Marshall Westbrook
// Date: 2/14/22
//*********************************************

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SudokuValidator {
    private static final List<Integer> numbersToCheck  = new ArrayList<>(Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9));
    private String originalInput = "";
    private String sudokuPuzzleDisplay = "";
    private int[][] sudokuPuzzle = new int[9][9];
    private int badRow = 10; // 10 = no bad rows
    private int badColumn = 10; // 10 = no bad columns
    public String formatError = "";

    public SudokuValidator(String inputText)
    {
        originalInput = inputText;
        inputText.trim();
        FormatSudoku(inputText);
        badRow = CheckRows();
        badColumn = CheckColumns();
    }

    private void FormatSudoku(String inputText) {
        try
        {
            int positionInString = 0;
            for (int i = 0; i < sudokuPuzzle[0].length; i++)
            {
                for (int j = 0; j < sudokuPuzzle[1].length; j++)
                {
                    sudokuPuzzle[i][j] = Integer.parseInt(String.valueOf(inputText.charAt(positionInString)));
                    positionInString += 2; //jump over the space
                    //sudokuPuzzleDisplay = (sudokuPuzzleDisplay + " " + "[" + i + "]" + "[" + j + "]" + sudokuPuzzle[i][j]);
                    sudokuPuzzleDisplay = (sudokuPuzzleDisplay + " " + sudokuPuzzle[i][j]);
                }
                sudokuPuzzleDisplay = (sudokuPuzzleDisplay + "\n");
            }
        }
        catch(Exception ex)
        {
            originalInput = "";
            sudokuPuzzleDisplay = "";
            sudokuPuzzle = new int[9][9];
            formatError = ex.getMessage();
        }

    }
    private int CheckRows()
    {
        int answer = 10;
        for (int i = 0; i < sudokuPuzzle[0].length; i++)
        {
            List<Integer> numbers = new ArrayList<>();
            for (int j = 0; j < sudokuPuzzle[1].length; j++)
            {
                if(numbers.contains(sudokuPuzzle[i][j]))
                {
                    answer = i;
                }
                else
                {
                    if(numbersToCheck.contains(sudokuPuzzle[i][j]))
                    {
                        numbers.add(sudokuPuzzle[i][j]);
                    }
                    else
                    {
                        answer = i;
                    }
                }
            }
        }
        return answer;
    }
    private int CheckColumns()
    {
        int answer = 10;
        for (int i = 0; i < sudokuPuzzle[0].length; i++)
        {
            List<Integer> numbers = new ArrayList<>();
            for (int j = 0; j < sudokuPuzzle[1].length; j++)
            {
                if(numbers.contains(sudokuPuzzle[j][i]))
                {
                    answer = i;
                }
                else
                {
                    if(numbersToCheck.contains(sudokuPuzzle[j][i]))
                    {
                        numbers.add(sudokuPuzzle[j][i]);
                    }
                    else
                    {
                        answer = i;
                    }
                }
            }
        }
        return answer;
    }
    public String GetSudokuDisplay()
    {
        return sudokuPuzzleDisplay;
    }
    public String GetOriginalInput()
    {
        return originalInput;
    }
    public int GetBadRow()
    {
        return badRow;
    }
    public int GetBadColumn()
    {
        return badColumn;
    }



}
