
import java.util.*;

abstract class Account {
    protected String id;
    protected long balance;

    Account(String id, long balance) {
        this.id = id;
        this.balance = balance;
    }

    abstract String withdraw(long amount);
}

class Savings extends Account {
    Savings(String id, long balance) {
        super(id, balance);
    }

    @Override
    String withdraw(long amount) {
        if (balance - amount < 1000) {
            return id + " rejected: minimum balance 1000";
        }

        balance -= amount;
        return id + " balance " + balance;
    }
}

class Current extends Account {
    Current(String id, long balance) {
        super(id, balance);
    }

    @Override
    String withdraw(long amount) {
        if (balance - amount < -5000) {
            return id + " rejected: overdraft limit 5000";
        }

        balance -= amount;
        return id + " balance " + balance;
    }
}

public class BankAccountSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Account> accounts = new HashMap<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();

            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.split("\\s+");

            if (parts[0].equals("Savings")) {
                accounts.put(parts[1],
                    new Savings(parts[1],
                        Long.parseLong(parts[2])));
            } else if (parts[0].equals("Current")) {
                accounts.put(parts[1],
                    new Current(parts[1],
                        Long.parseLong(parts[2])));
            } else if (parts[0].equals("WITHDRAW")) {
                String id = parts[1];
                long amount = Long.parseLong(parts[2]);

                Account account = accounts.get(id);

                if (account == null) {
                    System.out.println("Account not found");
                } else if (amount <= 0) {
                    System.out.println("Invalid withdrawal amount");
                } else {
                    System.out.println(account.withdraw(amount));
                }
            }
        }

        sc.close();
    }
}
