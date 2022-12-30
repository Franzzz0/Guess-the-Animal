package animals;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.*;

public class UI {
    private final Scanner scanner;
    private BinaryTree binaryTree;
    private final CommandProcessor cp;
    private final MessagesProcessor messages;
    private final String fileName;
    private final ObjectMapper objectMapper;

    public UI(String fileName, ObjectMapper objectMapper) {
        this.fileName = fileName;
        this.scanner = new Scanner(System.in);
        this.cp = new CommandProcessor();
        this.messages = new MessagesProcessor(this.cp);
        this.objectMapper = objectMapper;
    }

    public void start() {
        messages.greet();
        tryToLoadFile();
        showMenu();
        messages.bye();
    }

    private void showMenu() {
        label:
        while (true) {
            messages.printMenu();
            String input = scanner.nextLine();
            switch (input) {
                case "0":
                    break label;
                case "1":
                    playGame();
                    break;
                case "2":
                    messages.listAnimals(binaryTree.getLeafsValues());
                    break;
                case "3":
                    searchForAnimal();
                    break;
                case "4":
                    messages.printTreeStats(this.binaryTree);
                    break;
                case "5":
                    this.cp.printTree(this.binaryTree);
                    break;
                case "6":
                    deleteAnimal();
                    break;
            }
        }
    }

    private void deleteAnimal() {
        messages.askForSearchedAnimal();
        String searchedAnimal = cp.getAnimalName(scanner.nextLine());
        Node animal = binaryTree.findNode(searchedAnimal);
        if (animal == null) {
            messages.animalNotFound(searchedAnimal);
        } else if (animal == this.binaryTree.root()) {
            messages.cantDeleteRoot();
        } else {
            this.binaryTree.deleteLeaf(animal);
            messages.animalDeleted(searchedAnimal);
        }
        saveToFile();
    }

    private void searchForAnimal() {
        messages.askForSearchedAnimal();
        String searchedAnimal = cp.getAnimalName(scanner.nextLine());
        Node animal = binaryTree.findNode(searchedAnimal);
        if (animal == null) {
            messages.noFacts(searchedAnimal);
        } else {
            messages.printFacts(animal);
        }
    }

    private void tryToLoadFile() {
        try {
            Node root = objectMapper.readValue(new File(fileName), Node.class);
            this.binaryTree = new BinaryTree(root);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            askForAnimal();
        }
        messages.welcome();
    }

    private void askForAnimal() {
        messages.askForFavorite();
        Node favoriteAnimal = getAnimal(scanner.nextLine());
        System.out.println();
        this.binaryTree = new BinaryTree(favoriteAnimal);
    }

    private void playGame() {
        messages.letsPlay();

        while (true) {
            messages.gameInstructions();
            scanner.nextLine();

            Node firstAnimal = findAnimal();
            System.out.println(cp.isIt(firstAnimal.getValue()));
            while (true) {
                String answer = cp.processAnswer(scanner.nextLine());
                if (answer.equals("no")) {
                    messages.giveUp();
                    Node newAnimal = getAnimal(scanner.nextLine());
                    getFact(firstAnimal, newAnimal);
                    break;
                } else if (answer.equals("yes")) {
                    messages.printNice();
                    break;
                } else {
                    messages.askYesOrNo();
                }
            }
            messages.askToPlayAgain();
            String answer = cp.processAnswer(scanner.nextLine());
            while (answer.equals("unknown answer")) {
                messages.askYesOrNo();
                answer = cp.processAnswer(scanner.nextLine());
            }
            if (answer.equals("no")) {
                saveToFile();
                break;
            }
        }
    }

    private void saveToFile() {
        try {
            objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValue(new File(fileName), this.binaryTree.root());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private Node findAnimal() {
        Node node = this.binaryTree.root();
        while (true) {
            if (!node.hasChildren()) {
                return node;
            }
            System.out.println(cp.getQuestion(node.getValue()));
            while (true) {
                String answer = cp.processAnswer(scanner.nextLine());
                if (answer.equals("yes")) {
                    node = node.getRight();
                    break;
                } else if (answer.equals("no")) {
                    node = node.getLeft();
                    break;
                } else {
                    messages.askYesOrNo();
                }
            }
        }
    }

    private void getFact(Node lastNode, Node secondAnimal) {
        Node firstAnimal = new Node(lastNode.getValue());
        String fact;
        while (true) {
            messages.askForFact(firstAnimal.getValue(), secondAnimal.getValue());
            fact = scanner.nextLine().toLowerCase().trim();
            if (!cp.factIsCorrect(fact)) {
                messages.printStatementExamples();
                continue;
            }

            lastNode.setValue(fact);
            messages.askIfCorrectFor(secondAnimal.getValue());

            while (true) {
                String answer = scanner.nextLine();
                if (cp.processAnswer(answer).equals("yes")) {
                    lastNode.setRight(secondAnimal);
                    lastNode.setLeft(firstAnimal);
                    break;
                }
                if (cp.processAnswer(answer).equals("no")) {
                    lastNode.setRight(firstAnimal);
                    lastNode.setLeft(secondAnimal);
                    break;
                }
                messages.askYesOrNo();
            }
            break;
        }
        messages.printLearned(
                cp.getQuestion(fact),
                cp.getSpecificFact(lastNode, firstAnimal),
                cp.getSpecificFact(lastNode, secondAnimal)
        );
    }

    private Node getAnimal(String input) {
        return new Node(cp.getAnimalName(input));
    }
}
