public class Grading {
    public static void main(String[] args) {
        char[][] studentAnswers = { {'a','b','a','c','c','d','e','e','a','d'},
                                    {'d','b','a','b','c','a','e','e','a','d'},
                                    {'e','d','d','a','c','b','e','e','a','d'},
                                    {'d','b','d','c','c','d','a','e','a','d'}};
        char[] answers = {'d','b','d','c','c','d','a','e','a','d'};
        double[] studentGrades = new double[studentAnswers.length];
        double gradeAverage = 0;

        for (int i=0; i < studentAnswers.length; i++)
        {
            double total = 0;
            for (int j=0; j < studentAnswers[i].length; j++)
            {
                if(answers[j] == studentAnswers[i][j])
                {
                    total += 10;
                }
            }
            System.out.println("Student " + i + " received a " +  total + ".");
            studentGrades[i] = total;
        }
        double grades = 0;
        for(int i = 0; i < studentGrades.length; i++)
        {
            grades += studentGrades[i];
        }
        gradeAverage = grades / studentGrades.length;
        System.out.println("The average grade was a " + gradeAverage + ".");
    }
}
