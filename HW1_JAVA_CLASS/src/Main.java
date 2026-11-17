import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Message> messages = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // 7a 2 objects of each one by hardcoded
        try {
            messages.add(new BoardMessage("Dana", "cs party invite", "academic", "CS Class"));
            BoardMessage urgentBoard = new BoardMessage("Admin", "Server shuttind down for the duration of the party", "status", "school");
            urgentBoard.setPriority(PriorityType.URGENT);
            messages.add(urgentBoard);

            EmailMessage email1 = new EmailMessage("Noa", "Attached is our assignment PDF.", "homework", "project submission");
            email1.addAttachment(new File("tr1_solution", "pdf"));
            messages.add(email1);

            EmailMessage email2 = new EmailMessage("prof. admatai", "Grades are out. vekama od?", "grades", "quiz 1 results");
            messages.add(email2);

            messages.add(new SmsMessage("shemesh", "Hey, is tenzer still a meniac?", "personal", "0501234567"));
            messages.add(new SmsMessage("avi bitter", "boss, the turkish mafia payed us a visit.", "work", "0529876543"));
        } catch (Exception e) {
            System.out.println("Initialization error: " + e.getMessage());
        }

        boolean running = true;
        while (running) {
            System.out.println("\n=== Messaging System Menu ===");
            System.out.println("1. add a new message");
            System.out.println("2. delete a message");
            System.out.println("3. print all messages");
            System.out.println("4. count messages containing specific words");
            System.out.println("5. print digital messages only");
            System.out.println("6. print previews of all messages");
            System.out.println("0. exit");
            System.out.print("choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addMessageMenu(scanner, messages);
                    break;
                case "2":
                    deleteMessageMenu(scanner, messages);
                    break;
                case "3":
                    if (messages.isEmpty()) {
                        System.out.println("No messages in the system.");
                    } else {
                        for (int i = 0; i < messages.size(); i++) {
                            System.out.println(i + ": " + messages.get(i));
                        }
                    }
                    break;
                case "4":
                    System.out.print("enter words to search (separated by spaces): ");
                    String input = scanner.nextLine().trim();
                    if (input.isEmpty()) {
                        System.out.println("number of matching messages: 0");
                        break;
                    }
                    ArrayList<String> words = new ArrayList<>(Arrays.asList(input.split("\\s+")));
                    int count = 0;
                    for (Message msg : messages) {
                        if (msg.find(words)) {
                            count++;
                        }
                    }
                    System.out.println("number of matching messages: " + count);
                    break;
                case "5":
                    for (Message msg : messages) {
                        if (msg instanceof IDigital) {
                            IDigital digitalMsg = (IDigital) msg;
                            System.out.println(msg + " -> [" + digitalMsg.printCommunicationMethod() + "]");
                        }
                    }
                    break;
                case "6":
                    for (Message msg : messages) {
                        System.out.println(msg.generatePreview());
                    }
                    break;
                case "0":
                    running = false;
                    System.out.println("goodbye");
                    break;
                default:
                    System.out.println("invalid option. try again.");
            }
        }
        scanner.close();
    }

    private static void addMessageMenu(Scanner scanner, ArrayList<Message> messages) {
        try {
            System.out.println("Select type: 1 = Board, 2 = Email, 3 = SMS");
            String type = scanner.nextLine();

            System.out.print("sender: ");
            String sender = scanner.nextLine();
            System.out.print("content: ");
            String content = scanner.nextLine();
            System.out.print("category: ");
            String category = scanner.nextLine();

            if (type.equals("1")) {
                System.out.print("board name: ");
                String boardName = scanner.nextLine();
                BoardMessage bm = new BoardMessage(sender, content, category, boardName);
                System.out.print("Priority (REGULAR / URGENT): ");
                bm.setPriority(PriorityType.valueOf(scanner.nextLine().trim().toUpperCase()));
                messages.add(bm);
                System.out.println("boardMessage added!");
            } else if (type.equals("2")) {
                System.out.print("subject: ");
                String subject = scanner.nextLine();
                EmailMessage em = new EmailMessage(sender, content, category, subject);
                System.out.print("add an attachment? (y/n): ");
                if (scanner.nextLine().equalsIgnoreCase("y")) {
                    System.out.print("file name: ");
                    String fName = scanner.nextLine();
                    System.out.print("file type: ");
                    String fType = scanner.nextLine();
                    em.addAttachment(new File(fName, fType));
                }
                messages.add(em);
                System.out.println("emailMessage added!");
            } else if (type.equals("3")) {
                System.out.print("Phone number (05XXXXXXXX): ");
                String phone = scanner.nextLine();
                messages.add(new SmsMessage(sender, content, category, phone));
                System.out.println("SmsMessage added!");
            } else {
                System.out.println("unknown message type.");
            }
        } catch (IllegalArgumentException | SmsException e) {
            System.out.println("error creating message: " + e.getMessage());
        }
    }

    private static void deleteMessageMenu(Scanner scanner, ArrayList<Message> messages) {
        if (messages.isEmpty()) {
            System.out.println("no messages to delete.");
            return;
        }
        for (int i = 0; i < messages.size(); i++) {
            System.out.println(i + ": " + messages.get(i).generatePreview());
        }
        System.out.print("enter index to delete: ");
        try {
            int idx = Integer.parseInt(scanner.nextLine());
            messages.remove(idx);
            System.out.println("message deleted.");
        } catch (Exception e) {
            System.out.println("invalid index.");
        }
    }
}