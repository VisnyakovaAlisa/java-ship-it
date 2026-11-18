    package ru.yandex.practicum.delivery;

    import java.util.ArrayList;
    import java.util.List;
    import java.util.Scanner;

    public class DeliveryApp {

        private static final Scanner scanner = new Scanner(System.in);
        private static List<Parcel> allParcels = new ArrayList<>();
        private static List<Trackable> trackableParcels = new ArrayList<>();
        private static final ParcelBox<StandardParcel>   standardBox   = new ParcelBox<>(100);
        private static final ParcelBox<PerishableParcel> perishableBox = new ParcelBox<>(50);
        private static final ParcelBox<FragileParcel>    fragileBox    = new ParcelBox<>(30);
        public static void main(String[] args) {
            boolean running = true;
            while (running) {
                showMenu();
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        addParcel();
                        break;
                    case 2:
                        sendParcels();
                        break;
                    case 3:
                        calculateCosts();
                        break;
                    case 4:
                        printTrackableParcel();
                        break;
                    case 5:
                        showBoxContents();
                        break;
                    case 0:
                        running = false;
                        break;
                    default:
                        System.out.println("Неверный выбор.");
                }
            }
        }

        private static void showMenu() {
            System.out.println("Выберите действие:");
            System.out.println("1 — Добавить посылку");
            System.out.println("2 — Отправить все посылки");
            System.out.println("3 — Посчитать стоимость доставки");
            System.out.println("4 — Мониторинг доставки");
            System.out.println("5 — Показать содержимое коробки");;
            System.out.println("0 — Завершить");
        }

        // реализуйте методы ниже
        private static void addParcel() {
            int type = 0;
            while (true) {
                try {
                    System.out.println("Введите тип посылки (1 — стандартная, 2 — скоропортящаяся, 3 — хрупкая):");
                    type = Integer.parseInt(scanner.nextLine());
                    if (type >= 1 && type <= 3) {
                        break;
                    } else {
                        System.out.println("Ошибка: введите число от 1 до 3!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Нужно ввести число!");
                }
            }

            System.out.println("Введите описание:");
            String description = scanner.nextLine();

            System.out.println("Введите вес:");
            int weight = 0;
            while (true) {
                weight = scanner.nextInt();
                if (weight > 0) {
                    break;
                } else {
                    System.out.println("Вес должен быть положительным");
                }
            }   
            scanner.nextLine();

            System.out.println("Введите адрес доставки:");
            String deliveryAddress = scanner.nextLine();

            System.out.println("Введите день отправки:");
            int sendDay = 0;
            while (true) {
                sendDay = scanner.nextInt();
                if (sendDay >= 1 && sendDay <= 31) {
                    break;
                } else {
                    System.out.println("Ошибка: введите число от 1 до 31!");
                }
            }
            scanner.nextLine();

            switch (type) {
                case 1:
                    StandardParcel standard = new StandardParcel(description, weight, deliveryAddress, sendDay);
                    allParcels.add(standard);
                    standardBox.addParcel(standard);
                    System.out.println("Стандартная посылка добавлена.");
                    break;
                case 2:
                    System.out.println("Введите срок хранения (в днях):");
                    int timeToLive = 0;
                    while (true) {
                        timeToLive = scanner.nextInt();
                        if (timeToLive > 0) {
                            break;
                        } else {
                            System.out.println("Срок хранения должен быть положительным");
                        }
                    }
                    scanner.nextLine();
                    PerishableParcel perishable = new PerishableParcel(description, weight, deliveryAddress, sendDay, timeToLive);
                    allParcels.add(perishable);
                    perishableBox.addParcel(perishable);
                    System.out.println("Скоропортящаяся посылка добавлена.");
                    break;
                case 3:
                    FragileParcel fragile = new FragileParcel(description, weight, deliveryAddress, sendDay);
                    allParcels.add(fragile);
                    trackableParcels.add(fragile); 
                    fragileBox.addParcel(fragile);
                    System.out.println("Хрупкая посылка добавлена.");
                    break;
                default:
                    System.out.println("Неверный тип посылки.");
        }
    }
    


        private static void sendParcels() {
            // Пройти по allParcels, вызвать packageItem() и deliver()
            if (allParcels.isEmpty()) {
                System.out.println("Список посылок пуст");
                return;
            }
            for (Parcel parcel : allParcels) {
                parcel.packageItem();
                parcel.deliver();
            }
        }

        private static void calculateCosts() {
            int count = 0;
            if (allParcels.isEmpty()) {
                System.out.println("Список посылок пуст");
                return;
            }
            for (Parcel parcel : allParcels) {
                count += parcel.calculateDeliveryCost();
            }
            System.out.println("Общая стоимость: " + count);
        
        }
        private static void printTrackableParcel(){
            if (trackableParcels.isEmpty()) {
            System.out.println("Список отслеживаемых посылок пуст");
            return;
            }
            System.out.println("Введите текущее местоположение:");
            String location = scanner.nextLine();

            for (Trackable parcel : trackableParcels) {
                parcel.reportStatus(location);
            }
        }
        private static void showBoxContents(){
            int boxType;
            while (true) {
            try {
                System.out.println("Выберите тип коробки (1 — стандартная, 2 — скоропортящаяся, 3 — хрупкая):");
                boxType = Integer.parseInt(scanner.nextLine());
                if (boxType >= 1 && boxType <= 3) {
                    break;
                }
                System.out.println("Ошибка: введите число от 1 до 3!");
            } catch (NumberFormatException e) {
                System.out.println("Нужно ввести число!");
            }
            }
            List<? extends Parcel> parcels;
            switch (boxType) {
                case 1:
                    parcels = standardBox.getAllParcels();
                    break;
                case 2:
                    parcels = perishableBox.getAllParcels();
                    break;
                case 3:
                    parcels = fragileBox.getAllParcels();
                    break;
                default:
                    System.out.println("Неверный тип коробки.");
                    return;
            }

            if (parcels.isEmpty()) {
                System.out.println("Коробка пуста.");
                return;
            }

            for (Parcel p : parcels) {
                 System.out.println("Описание:" + p.description + " (вес: " + p.weight + ", адрес: " + p.deliveryAddress + ")");
            }
        }
    }
