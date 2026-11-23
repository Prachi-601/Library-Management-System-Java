import java.util.Scanner;

abstract class LibraryItems{
    private int bookId;
    private String bookName ;
    private String authorName ;
    private String issueStatus ;

    LibraryItems(int bookId, String bookName, String authorName, String issueStatus){
            this.bookId = bookId;
            this.bookName = bookName;
            this.authorName = authorName;
            this.issueStatus = issueStatus;
    }
    public int getBookId() {
        return bookId;
    }
    public String getBookName(){
        return bookName;
    }
    public String getAuthorName(){
        return authorName;
    }
    public String getIssueStatus(){
        return issueStatus;
    }
    public void setIssueStatus(String issueStatus){
        this.issueStatus = issueStatus;
    }

    abstract void displayBook();
    abstract void issueBook();
    abstract void returnBook();


}

class Book extends LibraryItems{
    Book(int bookId, String bookName,
          String authorName, String issueStatus){

        super(bookId, bookName, authorName, issueStatus);
    }
    void displayBook(){
        System.out.println("--------------------------");
        System.out.println("Book ID: " + getBookId());
        System.out.println("Book Name: " +  getBookName());
        System.out.println("Author Name: " + getAuthorName());
        System.out.println("Issue Status: " + getIssueStatus());
        System.out.println("--------------------------");

    }
    void issueBook(){
        if(getIssueStatus().equals("Available")){
            setIssueStatus("Issued");
            System.out.println("Book issued successfully.");
        }
        else{
            System.out.println("Book already issued.");
        }
    }
    void returnBook(){
        if(getIssueStatus().equals("Issued")){
            setIssueStatus("Available");
            System.out.println("Book returned successfully.");
        }
        else{
            System.out.println("Book already available.");
        }}
}

public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("========== Library Management System ==========\n");
        int size;
        while(true){
            System.out.print("How many books do you want to store: ");
            size = sc.nextInt();
            if (size <= 0){
                System.out.println("Invalid Size.");
            }else{
                break;
            }

        }

        Book[] books = new Book[size];
        int booksCount = 0 ;
        int choice ;

        do{
            System.out.println("\n1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch(choice){
                case 1:
                    if(booksCount <  books.length){
                        System.out.print("Enter Book Id: " );
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Book Name: ");
                        String bookName = sc.nextLine();
                        System.out.print("Enter Author Name: ");
                        String authorName = sc.nextLine();
                        System.out.println("1.Available  2.Issued");
                        System.out.print("Enter status choice: ");
                        int statuschoice = sc.nextInt();
                        String status = "Available";
                            if(statuschoice ==  1) {
                                status = "Available";
                            } else if (statuschoice == 2) {
                                status = "Issued";
                            } else{
                                System.out.println("Invalid choice!");
                                break;
                            }
                        books[booksCount] = new Book(id, bookName, authorName, status);
                        booksCount++;
                        System.out.println("Book Added Successfully.");
                    }
                    else{
                        System.out.println("You cannot add more books. Limit exceeded!");
                    }

                    break;
                case 2:
                    if(booksCount > 0 ) {
                        System.out.println("\nBooks: ");
                        for (int i = 0; i < booksCount; i++) {
                            books[i].displayBook();
                        }

                    }
                    else{
                        System.out.println("No books added yet.");
                    }

                    break;
                case 3:
                    System.out.print("Enter Book ID to issue: ");
                    int issueId = sc.nextInt();
                    boolean found = false;

                    for(int i = 0 ; i < booksCount ;i++){
                        if(books[i].getBookId() == issueId){
                            books[i].issueBook();
                            found = true;
                            break;
                        }
                    }
                    if(!found){
                        System.out.println("Book not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter Book ID to return: ");
                    int returnId = sc.nextInt();
                    boolean flag  = false;
                    for(int i = 0 ; i < booksCount ;i++){
                        if(books[i].getBookId() == returnId){
                            books[i].returnBook();
                            flag = true;
                            break;
                        }
                    }
                    if(!flag){
                        System.out.println("Book not found.");
                    }
                    break;
                case 5:
                    System.out.println("========== Thank You ==========");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        }while(choice != 5);
        sc.close();
    }
}