package ru.job4j.ood.lsp;

import ru.job4j.ood.ocp.WrongOCP2;

public class WrongLSP {
    /*  Нарушения LSP:
        1. Если понадобиться добавить транспорт, то потребуется исправлять код.
        2. Некоторые из методов, определенных в суперклассе не могут использоваться
        в каком-то подклассе. Например, машина не должна летать. Ослабление постусловий.
        3. Метод mayToUse ослаблен в постусловии в классе Bus.
     */

    public void moving(Transport transport) {
        if (transport.mayToUse()) {
            if (transport.getClass() == Bus.class) {
                transport.drive();
            }
            if (transport.getClass() == Airplane.class) {
                transport.fly();
            }
        }
    }

    public class Transport {

        private int ticketPrice;

        public void drive() {
        }

        public void fly() {
        }

        public boolean mayToUse() {
            return ticketPrice < 1000;
        }

        public int getTicketPrice() {
            return ticketPrice;
        }

        public void setTicketPrice(int ticketPrice) {
            this.ticketPrice = ticketPrice;
        }
    }

    public class Bus extends Transport {

        public boolean mayToUse(Transport transport) {
            return getTicketPrice() < 100;
        }
    }

    public class Airplane extends Transport {

    }
}
