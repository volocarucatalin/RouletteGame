package com.game.roullet.service;

import com.game.roullet.entity.Room;
import com.game.roullet.entity.Bet;
import com.game.roullet.rules.RouletteRules;

public class RouletteService {//implements RouletteRules {

//    final private PlayerService playerService;
//
//    public RouletteService(PlayerService playerService) {
//        this.playerService = playerService;
//    }
//
//    public Integer revealNumber() {
//        return (int) (Math.random() * ((MAX - MIN) + 1));
//    }
//
//    public void handleBets(Room room, Integer spinNumber) {
//        for (Bet bet : room.getBets()) {
//            switch (bet.getBetType()) {
//                case ROW:
//                    if (isRow(spinNumber, bet.getBetTypeValue())) {
//                        playerService.receiveWiningAmount(bet.getPlayerId(), (bet.getBetAmount() * 3));
//                    }
//                    break;
//
//                case COLUMN:
//                    if (isColumn(spinNumber, bet.getBetTypeValue())) {
//                        playerService.receiveWiningAmount(bet.getPlayerId(), (bet.getBetAmount() * 3));
//                    }
//                    break;
//
//                case ODD:
//                    if (isOdd(spinNumber)) {
//                        playerService.receiveWiningAmount(bet.getPlayerId(), (bet.getBetAmount() * 2));
//                    }
//                    break;
//
//                case EVEN:
//                    if (!isOdd(spinNumber)) {
//                        playerService.receiveWiningAmount(bet.getPlayerId(), (bet.getBetAmount() * 2));
//                    }
//                    break;
//
//                case RED:
//                    if (isRed(spinNumber)) {
//                        playerService.receiveWiningAmount(bet.getPlayerId(), (bet.getBetAmount() * 2));
//                    }
//                    break;
//
//                case BLACK:
//                    if (!isRed(spinNumber)) {
//                        playerService.receiveWiningAmount(bet.getPlayerId(), (bet.getBetAmount() * 2));
//                    }
//                    break;
//
//                case NUMBER:
//                    if (isTheSameNumber(spinNumber, bet.getBetTypeValue())) {
//                        playerService.receiveWiningAmount(bet.getPlayerId(), (bet.getBetAmount() * 36));
//                    }
//                    break;
//
//                default:
//                    throw new RuntimeException("Bet type " + bet.getBetType() + " is not valid");
//            }
//
//        }
//        room.removeAllBets();
//
//    }
//
//    @Override
//    public boolean isRow(int spinNumber, int betTypeValue) {
//        if (spinNumber == 0) {
//            return false;
//        }
//        return (spinNumber % 3)  == betTypeValue;
//    }
//
//    @Override
//    public boolean isColumn(int spinNumber, int betTypeValue) {
//        if (spinNumber == 0) {
//            return false;
//        }
//        if (isColumn3(spinNumber) && betTypeValue == 3) {
//            return true;
//        }
//        if (isColumn2(spinNumber) && betTypeValue == 2) {
//            return true;
//        }
//        if (isColumn1(spinNumber) && betTypeValue == 1) {
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public boolean isOdd(int spinNumber) {
//        if (spinNumber == 0) {
//            return false;
//        }
//        return spinNumber % 2 != 0;
//    }
//
//
//    private boolean isColumn3(int spinNumber) {
//        return spinNumber >= 25 && spinNumber <= 36;
//    }
//
//    private boolean isColumn2(int spinNumber) {
//        return spinNumber >= 13 && spinNumber <= 24;
//    }
//
//    private boolean isColumn1(int spinNumber) {
//        return spinNumber >= 1 && spinNumber <= 12;
//    }
//
//    @Override
//    public boolean isTheSameNumber(int spinNumber, int betTypeValue) {
//        return spinNumber == betTypeValue;
//    }
//
//    @Override
//    public boolean isRed(int spinNumber) {
//        if (spinNumber == 0) {
//            return false;
//        }
//        return red.contains(spinNumber);
//    }
}
