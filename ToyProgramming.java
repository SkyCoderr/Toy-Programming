import java.util.ArrayList;

public class ToyProgramming {

    private String str;
    private ArrayList<String> charList;
    private int[] numList;

    public ToyProgramming(String str){
        this.str = str;
        this.charList = new ArrayList<String>(0);
        this.numList = new int[100];
    }

    private boolean charInArray(String letter){
        for (int i = 0; i < charList.size(); i++){
            if (letter.equals(charList.get(i))){
                return true;
            }
        }
        return false;
    }

    private void assign(String el){
        String[] string = el.split(" ");
        if (charInArray(string[0])){
            numList[charList.indexOf(string[0])] = Integer.parseInt(string[2]);
        } else{
            charList.add(string[0]);
            numList[charList.indexOf(string[0])] = Integer.parseInt(string[2]);
        }
    }

    private int result(String el){
        String[] string = el.split(" ");
        try {
            int i = Integer.parseInt(string[0]);
            return i;
        } catch (NumberFormatException e) {
            return numList[charList.indexOf(string[0])];
        }
    }

    private void add(String el){
        String[] string = el.split(" ");
        int[] nums = new int[2];
        if (charInArray(string[0]) == false)
            charList.add(string[0]);
        try {
            nums[0] = Integer.parseInt(string[2]);
            try{
                nums[1] = Integer.parseInt(string[4]);
                numList[charList.indexOf(string[0])] = nums[0] + nums[1];
            } catch (NumberFormatException e){
                if (charInArray(string[4]))
                    numList[charList.indexOf(string[0])] = nums[0] + numList[charList.indexOf(string[4])];
                else{
                    charList.add(string[4]);
                    numList[charList.indexOf(string[0])] = nums[0] + numList[charList.indexOf(string[4])];
                }
            }
        } catch (NumberFormatException e) {
            if (charInArray(string[2])){
                try {
                    nums[1] = Integer.parseInt(string[4]);
                    numList[charList.indexOf(string[0])] = numList[charList.indexOf(string[2])] + nums[1];
                } catch (NumberFormatException e1){
                    if (charInArray(string[4])){
                        numList[charList.indexOf(string[0])] = numList[charList.indexOf(string[2])] +
                                numList[charList.indexOf(string[4])];
                    } else {
                        charList.add(string[4]);
                        numList[charList.indexOf(string[0])] = numList[charList.indexOf(string[2])] +
                                numList[charList.indexOf(string[4])];
                    }
                }
            } else{
                charList.add(string[2]);
                try {
                    nums[1] = Integer.parseInt(string[4]);
                    numList[charList.indexOf(string[0])] = numList[charList.indexOf(string[2])] + nums[1];
                } catch (NumberFormatException e1){
                    if (charInArray(string[4])){
                        numList[charList.indexOf(string[0])] = numList[charList.indexOf(string[2])] +
                                numList[charList.indexOf(string[4])];
                    } else {
                        charList.add(string[4]);
                        numList[charList.indexOf(string[0])] = numList[charList.indexOf(string[2])] +
                                numList[charList.indexOf(string[4])];
                    }
                }
            }
        }
    }

    public int showResult(){
        String[] elements = str.split("\n");
        String plus = "+";
        String equal = "=";
        int result = 0;
        for (int i = 0; i < elements.length; i++){
            String el = elements[i];
            if (el.indexOf(plus) != -1){
                add(el);
            }
            else if (el.indexOf(plus) == -1 && el.indexOf(equal) != -1){
                assign(el);
            }
            else if (el.indexOf(plus) == -1 && el.indexOf(equal) == -1){
                result = result(el);
            }
        }
        return result;
    }

    public static void main(String[] args){
        String t = "A = 5"+"\n"+"B = 8"+"\n"+"C = A + 15"+"\n"+"D = C + B"+"\n"+"C";
        ToyProgramming toy = new ToyProgramming(t);
        System.out.println(toy.showResult());
    }

}
