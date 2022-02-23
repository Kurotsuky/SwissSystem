package utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Utility
{
    public static int RandomNumber(int min, int max) 
    {
        return (int)Math.floor(Math.random()*((max-min+1)+min));
    }

    public static int AskInt() 
    {
        Scanner sc = new Scanner(System.in);
        
        int num;
        System.out.print("Introduce un número: ");
        num = sc.nextInt();

        sc.close();
        return num;
    }
    
    public static int AskInt(int min) 
    {
        Scanner sc = new Scanner(System.in);
        
        int num;
        
        do {
            System.out.print("Introduce un número: ");
            num = sc.nextInt();
        } while(num < min);
        
        sc.close();
        return num;
    }
    
    public static int AskInt(int min, int max) 
    {
        Scanner sc = new Scanner(System.in);
        
        int num;
        
        do {
            System.out.print("Introduce un número: ");
            num = sc.nextInt();
        } while(num < min || num > max);
        
        sc.close();
        return num;
    }
    
    public static int AskInt(String text) 
    {
        Scanner sc = new Scanner(System.in);
        
        int num;
        System.out.print(text);
        num = sc.nextInt();
        
        sc.close();
        return num;
    }
    
    public static int AskInt(int min, String text) 
    {
        Scanner sc = new Scanner(System.in);
        
        int num;
        
        do {
            System.out.print(text);
            num = sc.nextInt();
        } while(num < min);
        
        sc.close();
        return num;
    }
    
    public static int AskInt(String text, int max) 
    {
        Scanner sc = new Scanner(System.in);
        
        int num;
        
        do {
            System.out.print(text);
            num = sc.nextInt();
        } while(num > max);
        
        sc.close();
        return num;
    }
    
    public static int AskInt(String text, int min, int max) 
    {
        Scanner sc = new Scanner(System.in);
        
        int num;
        
        do {
            System.out.print(text);
            num = sc.nextInt();
        } while(num < min || num > max);
        
        sc.close();
        return num;
    }
    
    public static String AskString() 
    {
        Scanner sc = new Scanner(System.in);
        
        String text;
        System.out.print("Introduce un palabra o frase: ");
        text = sc.nextLine();
        
        sc.close();
        return text;
    }
    
    public static String AskString(String text) 
    {
        Scanner sc = new Scanner(System.in);
        
        System.out.print(text);
        text = sc.nextLine();
        
        sc.close();
        return text;
    }
    
    public static String[] AskStringArray(int lenght) 
    {
        String[] textArray = new String[lenght];
        Scanner sc = new Scanner(System.in);
        
        for(int i = 0; i < textArray.length; i++) 
        {
            System.out.print((i+1) + "º palabra o frase: ");
            textArray[i] = sc.nextLine();
            
            if(textArray[i] == null || textArray[i].length() != 9) 
            {
                textArray[i] = null;
                break;
            }
        }
        sc.close();
        return textArray;
    }
    
    public static String[] AskStringArray(String text, int lenght) 
    {
        String[] textArray = new String[lenght];
        Scanner sc = new Scanner(System.in);
        
        for(int i = 0; i < textArray.length; i++) 
        {
            System.out.print((i+1) + "º " + text + ": ");
            textArray[i] = sc.nextLine();
            
            if(textArray[i] == null || textArray[i].length() != 9) 
            {
                textArray[i] = null;
                break;
            }
        }
        sc.close();
        return textArray;
    }
    
    public static char AskChar(String text) 
    {
        Scanner sc = new Scanner(System.in);
        
        char letter;
        
        System.out.print(text);
        text = sc.nextLine();
        letter = text.charAt(0);
        
        sc.close();
        return letter;
    }
    
    public static int[] AskDate() 
    {
        int[] date = new int[3];
        int[] dateLimit = new int[] { 31, 12, 5000000 };
        String[] dateText = new String[] { "Dia", "Mes", "Año" };
        Scanner sc = new Scanner(System.in);
        
        for(int i = 0; i < date.length; i++) 
        {
            do {
                System.out.print("Introduzca el " + dateText[i] + ": ");
                try { date[i] = Integer.parseInt(sc.nextLine()); } 
                catch(Exception e) { date[i] = 0; break; } 
                
                if(date[i] >= dateLimit[i] || date[i] <= 0) 
                {
                    System.out.println("Número introducido no válido.");
                }
            } 
            while(date[i] >= dateLimit[i] || date[i] <= 0);
        }
        sc.close();
        return date;
    }
    
    public static int[] AskDate(String[] text) 
    {
        int[] date = new int[3];
        int[] dateLimit = new int[] { 31, 12, 5000000 };
        Scanner sc = new Scanner(System.in);
        
        for(int i = 0; i < date.length; i++) 
        {
            do {
                System.out.print(text[i]);
                try { date[i] = Integer.parseInt(sc.nextLine()); } 
                catch(Exception e) { date[i] = 0; break; } 
                
                if(date[i] >= dateLimit[i] || date[i] <= 0) 
                {
                    System.out.println("Número introducido no válido.");
                }
            } 
            while(date[i] >= dateLimit[i] || date[i] <= 0);
        }
        sc.close();
        return date;
    }
    
    public static String GetStringOfArray(String[] array) 
    {
        String string = "";
        
        for(int i = 0; i < array.length; i++) 
        {
            String currentElement = array[i];
            if(currentElement != null && !currentElement.isEmpty()) 
            {
                string += currentElement + ", ";
            }
        }
        if(string.length() >= 2) 
        {
            string = string.substring(0, (string.length()-2));
        }
        return string;
    }
    
    public static String[] GetArrayOfString(String string) 
    {
        StringTokenizer st = new StringTokenizer(string, "\n"); 
        String[] array = new String[st.countTokens()];
        
        for(int i = 0; i < array.length; i++) 
        {
            array[i] = st.nextToken();
        } 
        return array;
    }
    
    public static int GetMenuOption(int numOptions, String[] textOptions) 
    {
        int userNum;
        int i;
        
        do {
            for(i = 0; i < numOptions; i++) 
            {
                System.out.println((i+1) + ". " + textOptions[i]);
            }
            System.out.println((i+1) + ". Salir");
            System.out.println("");

            userNum = AskInt("Introduce una opcion: ");
        } while(userNum > numOptions+1 || userNum < 1);
        
        return userNum;
    }
    
    public static String[] fileToArray(String filePath) throws FileNotFoundException 
    {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        String fileText = "";
        
        while(scanner.hasNextLine()) 
        {
            fileText += scanner.nextLine();
            
            if(scanner.hasNextLine()) 
            {
                fileText += ",";
            }
        }
        scanner.close();
        return fileText.split(",");
    }
        
    public static boolean arrayToFile(String[] array, String filePath) throws FileNotFoundException, IOException 
    {
        try {
            File file = new File(filePath);
            FileWriter fl = new FileWriter(file);

            for(int i = 0; i < array.length; i++) 
            {
                fl.write(array[i]);
                fl.write(",");
            }
            fl.close();
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }
    
    public static String[] GetFileText(File file, String delim) throws FileNotFoundException 
    {
        Scanner fileScanner = new Scanner(file);
        String text = "";
        
        while(fileScanner.hasNextLine()) 
        {
            text += fileScanner.nextLine();
            
            if(fileScanner.hasNextLine()) 
            {
                text += delim;
            }
        }
        fileScanner.close();
        text = text.replace("\\" + delim, delim);
        return GetStringText(text, delim);
    }
    
    public static String[] GetStringText(String text, String delim) 
    {
        String[] arrayText = text.split(delim);
        return arrayText;
    }
    
    public static String[] RemoveEmptyStrings(String[] array) 
    {
        ArrayList<String> list = ArrayStringToList(array);
        list.removeIf(String::isEmpty);
        array = ListStringToArray(list);
        return array;
    }
    
    public static ArrayList<String> ArrayStringToList(String[] array) 
    {
        ArrayList<String> list = new ArrayList<String>();
        
        for(int i = 0; i < array.length; i++) 
        {
            list.add(array[i]);
        }
        return list;
    }
    
    public static String[] ListStringToArray(ArrayList<String> list) 
    {
        String[] array = new String[list.size()];
        
        for(int i = 0; i < array.length; i++) 
        {
            array[i] = list.get(i);
        }
        return array;
    }
}
