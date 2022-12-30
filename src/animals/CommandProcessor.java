package animals;

import java.util.ResourceBundle;

public class CommandProcessor {
    private final ResourceBundle patterns;

    public CommandProcessor() {
        this.patterns = ResourceBundle.getBundle("patterns");
    }

    public String processAnswer(String answer) {
        String yesPattern = patterns.getString("positiveAnswer.isCorrect");
        String noPattern = patterns.getString("negativeAnswer.isCorrect");

        if (answer.trim().toLowerCase().matches(yesPattern)) {
            return "yes";
        } else if (answer.trim().toLowerCase().matches(noPattern)) {
            return "no";
        } else {
            return "unknown answer";
        }
    }

    public String getQuestion(String fact) {
        return fact
                .replaceFirst(patterns.getString("question.1.pattern"), patterns.getString("question.1.replace"))
                .replaceFirst(patterns.getString("question.2.pattern"), patterns.getString("question.2.replace"));
    }

    public String getFact(Node parentNode, Node children) {
        String fact;
        if (parentNode.getLeft() == children) {
            fact = parentNode.getValue()
                    .replaceFirst(patterns.getString("negative.1.pattern"), patterns.getString("negative.1.replace"))
                    .replaceFirst(patterns.getString("negative.2.pattern"), patterns.getString("negative.2.replace"))
                    .replaceFirst(patterns.getString("negative.3.pattern"), patterns.getString("negative.3.replace"));
        } else {
            fact = parentNode.getValue();
        }
        return fact;

    }

    public String getSpecificFact(Node parentNode, Node animal) {
        String fact = this.getFact(parentNode, animal);
        fact = fact.replaceFirst(
                patterns.getString("animalFact.1.pattern"),
                patterns.getString("animalFact.1.replace"));
        String name = animal.getValue().replaceFirst(
                patterns.getString("definite.1.pattern"),
                patterns.getString("definite.1.replace"));
        return String.format(fact, name);
    }

    public String getAnimalName(String input) {
        String name = input.toLowerCase();

        if (name.matches(patterns.getString("animal.1.pattern"))) {
            return name.replaceFirst(patterns.getString("animal.1.pattern"), patterns.getString("animal.1.replace"));
        }
        if (name.matches(patterns.getString("animal.2.pattern"))) {
            return name.replaceFirst(patterns.getString("animal.2.pattern"), patterns.getString("animal.2.replace"));
        }
        if (name.matches(patterns.getString("animal.3.pattern"))) {
            return name.replaceFirst(patterns.getString("animal.3.pattern"), patterns.getString("animal.3.replace"));
        }
        return name;
    }

    public void printFacts(Node animal) {
        if (animal.getParent() == null) {
            return;
        }
        printFacts(animal.getParent());
        System.out.printf(" - %s%n", getFact(animal.getParent(), animal));
    }

    public void printTree(BinaryTree tree) {
        System.out.println();
        printNodes(tree.root(), "", 0);
    }

    private void printNodes(Node current, String branch, int depth) {
        if (current == null) return;
        String value = current.hasChildren() ? getQuestion(current.getValue()) : current.getValue();
        String newBranch = branch(current, branch, depth);
        System.out.printf("%s%s%n", newBranch, value);
        printNodes(current.getRight(), newBranch, current.getNodeDepth());
        printNodes(current.getLeft(), newBranch, current.getNodeDepth());
    }

    private String branch(Node current, String oldBranch, int oldDepth) {
        if (current.getParent() == null) {
            return " └ ";
        }
        int depth = current.getNodeDepth();
        if (depth > oldDepth) {
            oldBranch = oldBranch.replaceAll("├", "│").replaceAll("└", " ")
                    .substring(0, oldBranch.length());
        } else if (depth == oldDepth) {
            oldBranch = oldBranch.substring(0, oldBranch.length() - 1);
        } else {
            oldBranch = oldBranch.substring(0, oldBranch.length() - 2);
        }
        StringBuilder branch = new StringBuilder(oldBranch);

        if (current.getParent().getRight() == current) {
            branch.append("├ ");
        } else {
            branch.append("└ ");
        }
        return branch.toString();
    }

    public boolean factIsCorrect(String fact) {
        return fact.matches(patterns.getString("statement.1.pattern"));
    }

    public String isIt(String animal) {
        return animal.replaceFirst
                (patterns.getString("guessAnimal.1.pattern"), patterns.getString("guessAnimal.1.replace"));
    }

    public String getNameWithoutArticle(String animal) {
        return animal.replaceFirst(
                patterns.getString("animalName.1.pattern"),
                patterns.getString("animalName.1.replace"));
    }
}
