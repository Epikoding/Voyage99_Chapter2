class Taxi {
    //  필드
    int aTaxiNumber; //택시번호
    int gasLeft = 15; //주유량
    int currentSpeed = 0; //현재속도
    int basicDistance = 5; //기본거리
    int distanceToDestination; //목적지까지 거리
    int basicFare = 3500; //기본 요금
    int farForDistance = 500; //거리당 요금

    String drivable = "일반"; // 상태

    //  생성자
    Taxi(int gasLeft) {
        this.aTaxiNumber = (int) (Math.random() * 10000 + 1);
        System.out.println("택시번호: " + aTaxiNumber);
        isDrivable();
    }

    //  메소드
    boolean isDrivable() {
        if (gasLeft >= 10) {
            System.out.println("운행이 가능합니다.");
            return true;
        } else {
            System.out.println("운행이 불가합니다.");
            return false;
        }
    }

    boolean onBoard() {
        if (isDrivable() && drivable.equals("일반")) {
            drivable = "운행 중";
        } else {
            drivable = "탑승 불가";
        }
        //- 승객 탑승은 택시 상태가 ‘일반'일 때만 가능합니다.
        //- 그 외 택시는 ‘탑승 불가’ 처리를 해주세요.
        //- ‘일반’ 상태의 택시가 승객을 태우면 ‘운행 중’ 상태로 변경해 주세요
        return false;
    }

    void changeSpeed(int acceleration) {
        if (drivable.equals("운행 중")) {
            currentSpeed += acceleration;
            System.out.println("현재 속도는 " + currentSpeed + "입니다.");
        }
    }

    int isFareTo(int distanceToDestination) {
        this.distanceToDestination = distanceToDestination;
        if (distanceToDestination > basicDistance) {
            basicFare += (distanceToDestination - basicDistance) * farForDistance;
        } else {
            return basicFare;
        }
        System.out.println("최종 요금은 " + basicFare + "입니다.");
        return basicFare;
    }
}
class Bus {
    //  필드
    int busNumber; // 버스 번호
    int maxPassengerNumber = 10; // 최대 승객수
    int currentPassengerNumber = 0; // 현재 승객수
    int gasRemaining = 12; // 주유량
    int currentSpeed; // 현재 속도
    int acceleration = 0; // 가속
    String status = "운행"; // 버스 상태

    //  생성자
    Bus() {
        this.busNumber = (int) (Math.random() * 10000 + 1);
        System.out.println("버스번호:" + busNumber);
    }

    //  메소드
    boolean isLeftGas() {
        return gasRemaining >= 10;
    }

    boolean isOnBoardable() {
        return maxPassengerNumber >= currentPassengerNumber;
    }

    boolean drive() {
        if (isLeftGas()) {
            System.out.println("기름이 " + gasRemaining + "남았습니다. 운행 중입니다.");
            gasRemaining -= 1;
            return true;
        }

        if (!isLeftGas()) {
            System.out.println("주유가 필요합니다.");
            status = "차고지행";
        }
        return true;
    }

    //  속도 변경
    int changeSpeed(int acceleration) {
        this.acceleration = acceleration;

        if (isLeftGas()) {
            currentSpeed += acceleration;
        }
        System.out.println("현재 속도는 " + currentSpeed + "입니다.");
        return currentSpeed;
    }

    //  승객 탑승
    int onBoard(int passenger) {
        currentPassengerNumber += passenger;
        while (isOnBoardable()) {
            if (status.equals("운행")) {
                System.out.println("승객 " + passenger + "명이 탑승했습니다. " + "현재 승객의 수는 " + currentPassengerNumber + "명입니다.");
            }
            break;
        }
        if (!isOnBoardable()) {
            System.out.println(currentPassengerNumber - maxPassengerNumber + "명이 초과되어 더 이상 승객을 태울 수 없습니다.");
        }
        return currentPassengerNumber;
        /* 변수: 운행중이냐, 승객 수가 적냐
        1. 운행중이고 승객 수가 45명 이하일 때 -> 운행
        2. 운행중이고 승객 수가 46명 초과일 때 -> 불가
        3. 운행중이 아니고 승객 수가 45명 이하일 때 -> 불가
        4. 운행중이 아니고 승객 수가 46명 이상일 때 -> 불가 */
    }

}

public class Main {
    public static void main(String[] args) {
        Bus bus = new Bus();

        bus.onBoard(7);
        bus.drive();
        bus.changeSpeed(10);
        System.out.println("-----" + bus.status + "-----");
        bus.changeSpeed(-10);

        bus.onBoard(3);
        bus.drive();
        bus.changeSpeed(20);
        bus.changeSpeed(-20);
        System.out.println("-----" + bus.status + "-----");

        bus.onBoard(1);
        bus.drive();
        bus.changeSpeed(30);
        bus.changeSpeed(-30);
        System.out.println("-----" + bus.status + "-----");

        bus.onBoard(1);
        bus.drive();
        bus.changeSpeed(40);
        bus.changeSpeed(-40);
        System.out.println("-----" + bus.status + "-----");

//        Taxi taxi = new Taxi(15);
//        taxi.onBoard();
//        taxi.changeSpeed(20);
//        taxi.changeSpeed(50);
//        taxi.isFareTo(10);
    }
}

