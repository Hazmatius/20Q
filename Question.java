import java.util.*;
import java.io.*;
public class Question implements java.io.Serializable
{
    public static Scanner in = new Scanner(System.in);
    public String question;
    public Question parent = null;
    public Question yes = null;
    public Question no = null;
    public boolean terminal = false;
    public String answer;
    
    public Question(String q){
        this.question=q;
    }
    
    public void learn(){
        System.out.print("I give up! What were you thinking of? ");
        String ansB=in.nextLine();
        System.out.print("What is a question to which the answer is 'no' for "+question+" but 'yes' for "+ansB+"?\nQuestion: ");
        String ansC=in.nextLine();
        Question A = this;
        Question B = new Question(ansB);
        Question C = new Question(ansC);
        
        C.answer=A.answer;
        A.answer="no";
        B.answer="yes";
        
        C.parent=A.parent;
        A.parent=C;
        B.parent=C;
        
        C.yes=B;
        C.no=A;
        
        A.terminal=true;
        B.terminal=true;
        
        if(C.answer.equals("yes")){
            C.parent.yes=C;
        }
        else if(C.answer.equals("no")){
            C.parent.no=C;
        }
    }
    
    public void ask(){
        if(terminal){
            System.out.print("Are you thinking of "+question+"? (yes/no): ");
        }
        else{
            System.out.print(question + " (yes/no): ");
        }
        String ans=in.nextLine();
        if(ans.equals("yes")){
            if(yes==null){
                System.out.println("Hooray, I've won!");
            }
            else
            {
                yes.ask();
            }
        }
        else
        {
            if(no==null){
                learn();
            }
            else
            {
                no.ask();
            }
        }
    }
    
    public void printQuestion(String space){
        System.out.println(space+answer+" "+question);
        if(yes!=null){
            yes.printQuestion(space+space);
        }
        if(no!=null){
            no.printQuestion(space+space);
        }
    }
}