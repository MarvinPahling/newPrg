package cardmaster;

import cardmaster.cards.Card;

import java.util.Scanner;

public class PlayerConsole {

    public static void main(String[] args) {
        Game game = new Game(3, new CardFactory());
        PlayerConsole playerConsole = new PlayerConsole();
        playerConsole.run(game);
    }

    public void run(Game game) {
        Scanner scanner = new Scanner(System.in);

        while (game.getMode() != Game.Mode.END) {
            switch (game.getMode()) {
                case SHOPPING:
                    buyItems(game, scanner);
                    break;
                case PLAYING:
                    playGame(game, scanner);
                    break;
            }
        }

        System.out.println("GAME ENDED\nTHE FINAL SCORE IS:\n");
        this.displayCredits(game.getCredits());
    }

    private void buyItems(Game game, Scanner scanner) {
        //display Offers
        game.displayOffers();

        int input = scanner.nextInt();
        if(input > game.getShopItemCount() || input < 1) {
            game.endShopping();
            return;
        }

        if(!game.buy(input - 1)) {
            System.out.println("Nicht genügend Credits für diese Operation.");
        }
    }

    private void playGame(Game game, Scanner scanner) {
        Card card = null;
        int stack = -1;

        while (stack == -1) {
            card = this.chooseCard(game, scanner);
            stack = this.chooseStack(game, scanner);
        }

        game.play(card, stack);
    }

    private void displayCredits(double credits) {
        System.out.println("Credits: " + credits);
    }

    private void displayStacks(Game game) {
        this.displayCredits(game.getCredits());

        StringBuilder builder = new StringBuilder("\n");

        for(Shape shape : game.getTopShapes()) {
            if(shape == null) {
                builder.append("_").append("  ");
                continue;
            }
            builder.append(shape).append("  ");
        }
        builder.append("\n");

        for (int i = 0; i < game.getStacksCount(); i++) {
            builder.append(i+1).append("  ");
        }
        builder.append("\n");

        System.out.println(builder.toString());
    }

    private Card chooseCard(Game game, Scanner scanner) {
        this.displayStacks(game);

        StringBuilder builder = new StringBuilder();

        builder.append("Choose a card to play:\n");

        for(int i = 0; i < game.getHandCardsCount(); i++) {
            builder.append(i+1).append(": ").append(game.getHandCard(i).toString()).append("\n");
        }

        System.out.println(builder);

        int input = scanner.nextInt();

        if(input < 1 || input > game.getHandCardsCount()) {
            System.out.println("Not a valid number, try again.");
            return chooseCard(game, scanner);
        }

        return game.getHandCard(input - 1);
    }

    private int chooseStack(Game game, Scanner scanner) {
        this.displayStacks(game);

        System.out.println("Choose the desired stack or " + (game.getStacksCount() + 1) + " to select another card.");
        int stack = scanner.nextInt();

        if (stack <= 0 || stack >= game.getStacksCount() + 1) {
            return -1;
        }
        return stack - 1;
    }


}
