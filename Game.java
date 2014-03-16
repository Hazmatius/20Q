import java.util.*;
import java.io.*;
public class Game
{
    public static void main(String[] args){
        //Make sure to install the file saveFile.save in your root directory.
        
        System.out.println("Note: Make sure that saveFile.save is installed in your root folder so that I can remember what I learn.\n");
        
        Scanner in = new Scanner(System.in);
        
        Question root = new Question("Are you thinking of something abstract?");
        Question y = new Question("math");
        y.terminal=true;
        Question n = new Question("a rock");
        n.terminal=true;
        
        root.yes=y;
        root.no=n;
        y.answer="yes";
        n.answer="no";
        y.parent=root;
        n.parent=root;
        
        Question posRoot = load();
        if(posRoot!=null){
            root=posRoot;
        }
        
        boolean play = true;
        while(play){
            root.ask();
            System.out.print("Would you like to play again? (yes/no): ");
            String ans=in.nextLine();
            System.out.println();
            play=ans.equals("yes");
        }
        System.out.print("\nWould you like to save? (yes/no): ");
        String ans=in.nextLine();
        if(ans.equals("yes")){
            save(root);
        }
        System.out.println("\nThank you for playing.");
    }
    
    public static void save(Question q){
        try{
            FileOutputStream saveFile = new FileOutputStream("/saveFile.save");
            ObjectOutputStream save = new ObjectOutputStream(saveFile);
            save.writeObject(q);
            save.close();
        }
        catch(IOException e){
            
        }
    }
    
    public static Question load(){
        Question root = null;
        try{
            FileInputStream saveFile = new FileInputStream("/saveFile.save");
            ObjectInputStream restore = new ObjectInputStream(saveFile);
            try{
                root = (Question)restore.readObject();
                restore.close();
            }
            catch(ClassNotFoundException c){
                
            }
        }
        catch(IOException e){
            
        }
        return root;
    }
}