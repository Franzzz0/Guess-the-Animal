package animals;

import java.text.MessageFormat;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class MessagesProcessor {
    private final ResourceBundle messages;
    private final CommandProcessor cp;
    private final Random rnd;

    public MessagesProcessor(CommandProcessor cp) {
        this.messages = ResourceBundle.getBundle("messages");
        this.cp = cp;
        this.rnd = new Random();
    }

    public void greet() {
        LocalTime now = LocalTime.now();
        if (now.isAfter(LocalTime.of(5, 0)) && now.isBefore(LocalTime.NOON)) {
            System.out.println(messages.getString("greeting.morning"));
        } else if (!now.isBefore(LocalTime.NOON) && now.isBefore(LocalTime.of(18, 0))) {
            System.out.println(messages.getString("greeting.afternoon"));
        } else {
            System.out.println(messages.getString("greeting.evening"));
        }
        System.out.println();
    }

    public void bye() {
        String[] byeList = messages.getString("farewell").split("\f");
        System.out.println();
        System.out.println(byeList[rnd.nextInt(byeList.length)]);
    }

    public void welcome() {
        System.out.println(messages.getString("welcome"));
        System.out.println();
    }

    public void askForFavorite() {
        System.out.println(messages.getString("animal.wantLearn"));
        System.out.println(messages.getString("animal.askFavorite"));
        System.out.print("> ");
    }

    public void printMenu() {
        System.out.println(messages.getString("menu.property.title"));
        System.out.printf("1. %s%n", messages.getString("menu.entry.play"));
        System.out.printf("2. %s%n", messages.getString("menu.entry.list"));
        System.out.printf("3. %s%n", messages.getString("menu.entry.search"));
        System.out.printf("4. %s%n", messages.getString("menu.entry.statistics"));
        System.out.printf("5. %s%n", messages.getString("menu.entry.print"));
        System.out.printf("6. %s%n", messages.getString("menu.entry.delete"));
        System.out.printf("0. %s%n", messages.getString("menu.property.exit"));
        System.out.print("> ");
    }

    public void letsPlay() {
        System.out.println(messages.getString("game.letsPlay"));
    }

    public void gameInstructions() {
        System.out.println(messages.getString("game.think"));
        System.out.println(messages.getString("game.enter"));
        System.out.print("> ");
    }

    public void askYesOrNo() {
        String[] yesOrNo = messages.getString("ask.again").split("\f");
        System.out.println(yesOrNo[rnd.nextInt(yesOrNo.length)]);
    }

    public void giveUp() {
        System.out.println(messages.getString("game.giveUp"));
    }

    public void askForFact(String animal1, String animal2) {
        System.out.println(MessageFormat.format(messages.getString("statement.prompt"), animal1, animal2));
        System.out.print("> ");
    }

    public void printStatementExamples() {
        System.out.println(messages.getString("statement.error"));
    }

    public void askIfCorrectFor(String animal) {
        System.out.println(MessageFormat.format(messages.getString("game.isCorrect"), animal));
    }

    public void printLearned(String question, String fact1, String fact2) {
        System.out.println(messages.getString("game.learned"));
        System.out.println(fact1);
        System.out.println(fact2);
        System.out.println(messages.getString("game.distinguish"));
        System.out.println(" - " + question);
        printNice();
    }

    public void printNice() {
        String[] nice = messages.getString("animal.nice").split("\f");
        System.out.printf("%s%s%n%n", nice[rnd.nextInt(nice.length)], messages.getString("animal.learnedMuch"));
    }

    public void askToPlayAgain() {
        String[] again = messages.getString("game.again").split("\f");
        System.out.println(again[rnd.nextInt(again.length)]);
    }

    public void listAnimals(List<String> list) {
        System.out.println(messages.getString("tree.list.animals"));
        for (String animal : list) {
            System.out.printf(" - %s%n", cp.getNameWithoutArticle(animal));
        }
        System.out.println();
    }

    public void askForSearchedAnimal() {
        System.out.println(messages.getString("animal.prompt"));
    }

    public void noFacts(String searchedAnimal) {
        System.out.println(MessageFormat
                .format(messages.getString("tree.search.noFacts"), cp.getNameWithoutArticle(searchedAnimal)));
    }

    public void printFacts(Node animal) {
        System.out.println(MessageFormat
                .format(messages.getString("tree.search.facts"), cp.getNameWithoutArticle(animal.getValue())));
        cp.printFacts(animal);
        System.out.println();
    }

    public void printTreeStats(BinaryTree binaryTree) {
        TreeStatistics tree = new TreeStatistics(binaryTree);
        System.out.println(messages.getString("tree.stats.title"));
        System.out.println(MessageFormat
                .format(messages.getString("tree.stats.root"), tree.getRootValue()));
        System.out.println(MessageFormat
                .format(messages.getString("tree.stats.nodes"), tree.getNumberOfNodes()));
        System.out.println(MessageFormat
                .format(messages.getString("tree.stats.animals"), tree.getNumberOfLeafs()));
        System.out.println(MessageFormat
                .format(messages.getString("tree.stats.statements"), tree.getNumberOfParentNodes()));
        System.out.println(MessageFormat
                .format(messages.getString("tree.stats.height"), tree.getHeight()));
        System.out.println(MessageFormat
                .format(messages.getString("tree.stats.minimum"), tree.minimumDepth()));
        System.out.println(MessageFormat
                .format(messages.getString("tree.stats.average"), tree.avgDepth()));
        System.out.println();
    }

    public void animalNotFound(String searchedAnimal) {
        System.out.println(MessageFormat
                .format(messages.getString("tree.delete.fail"), cp.getNameWithoutArticle(searchedAnimal)));
    }

    public void cantDeleteRoot() {
        System.out.println(messages.getString("tree.delete.root"));
    }

    public void animalDeleted(String searchedAnimal) {
        System.out.println(MessageFormat
                .format(messages.getString("tree.delete.successful"), cp.getNameWithoutArticle(searchedAnimal)));
    }
}
