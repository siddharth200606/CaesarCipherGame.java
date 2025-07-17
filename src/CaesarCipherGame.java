import java.util.Scanner;

public class CaesarCipherGame {

    // Encrypts a message using Caesar Cipher
    public static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isLowerCase(character) ? 'a' : 'A';
                char shifted = (char) ((character - base + shift + 26) % 26 + base);
                result.append(shifted);
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }

    // Decrypts a message using Caesar Cipher
    public static String decrypt(String text, int shift) {
        return encrypt(text, 26 - shift);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain;

        // 🎨 ASCII Art Title
        System.out.println("   ____                              ____  _       _            ");
        System.out.println("  / ___|__ _ ___  ___ __ _ _ __     / ___|| | ___ | |__   __ _ ");
        System.out.println(" | |   / _` / __|/ __/ _` | '_ \\____\\___ \\| |/ _ \\| '_ \\ / _` |");
        System.out.println(" | |__| (_| \\__ \\ (_| (_| | | | |_____) | | (_) | |_) | (_| |");
        System.out.println("  \\____\\__,_|___/\\___\\__,_|_| |_|    |___/|_|\\___/|_.__/ \\__,_|");
        System.out.println("\n             🔐 Caesar Cipher Challenge 🔐");
        System.out.println("--------------------------------------------------");

        do {
            System.out.print("\n👤 Player 1 - Enter a secret message: ");
            String message = scanner.nextLine();

            int key;
            while (true) {
                System.out.print("🔑 Enter a secret key (0–25): ");
                key = scanner.nextInt();
                if (key >= 0 && key <= 25) break;
                System.out.println("❌ Please enter a valid key between 0 and 25.");
            }

            scanner.nextLine(); // consume leftover newline
            String encrypted = encrypt(message, key);

            System.out.println("\n✅ Message encrypted successfully!");
            System.out.println("--------------------------------------------------");
            System.out.println("\n👤 Player 2 - Time to guess the key!");
            System.out.println("Encrypted message: " + encrypted);

            int attempts = 0;
            boolean guessedCorrectly = false;
            int maxAttempts = 5;

            while (attempts < maxAttempts) {
                System.out.print("\n🔍 Attempt " + (attempts + 1) + "/" + maxAttempts + " - Guess the key: ");
                int guess = scanner.nextInt();
                attempts++;

                String decryptedGuess = decrypt(encrypted, guess);
                System.out.println("📝 Decrypted with key " + guess + ": " + decryptedGuess);

                if (guess == key) {
                    System.out.println("\n🎉 Correct! You cracked the code in " + attempts + " attempt(s)!");
                    System.out.println("🏆 Score: " + (maxAttempts - attempts + 1) * 20 + " points");
                    guessedCorrectly = true;
                    break;
                } else if (guess < key) {
                    System.out.println("❗ Hint: Your guess is too low.");
                } else {
                    System.out.println("❗ Hint: Your guess is too high.");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("\n😢 You've used all attempts. Game over!");
                System.out.println("🔓 The correct key was: " + key);
                System.out.println("📝 Original message: " + message);
            }

            System.out.print("\n🔁 Do you want to play again? (yes/no): ");
            scanner.nextLine(); // consume leftover newline
            String response = scanner.nextLine().trim().toLowerCase();
            playAgain = response.equals("yes");

        } while (playAgain);

        System.out.println("\n👋 Thanks for playing Caesar Cipher Challenge!");
        System.out.println("Post your score wherever you want to  and challenge a friend! 💼🎮");
        scanner.close();
    }
}
