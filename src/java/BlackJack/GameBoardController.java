package BlackJack;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

/**
 * Created by Hodei Eceiza
 * Date: 11/30/2020
 * Time: 15:50
 * Project: BlackJackOOAD
 * Copyright: MIT
 */
public class GameBoardController {

    public Button hit;
    public Button stay;
    public Button rules;
    public Button end;
    public Label balance;
    public DialogPane rulesPanel;
    public HBox dealerBox;
    public HBox dealerbackground;
    public HBox activePlayer;
    public HBox player2;
    public HBox player3;
    public Label handValue;
    @FXML
    private AnchorPane gameBoardPane;
    //  private ModelTest modelTest;
    private BlackJackLogic modelTest;
    private Rectangle rect = new Rectangle();


    /*
        public GameBoardController(ModelTest modelTest) {
            this.modelTest = modelTest;
        }

     */
    public GameBoardController(BlackJackLogic modelTest) {

        this.modelTest = modelTest;
    }


    public void initialize() {
        setListener(modelTest.activePlayer.hand, activePlayer);

        setListener(modelTest.dealer1.hand, dealerBox);

        rulesPanel.setVisible(false);

        setButtonListener();

//Listens changes of the observableList


//balance its binded, should do a double bind? or call method from logic to change the balance?
        // balance.textProperty().bind(modelTest.balanceProperty());
//        handValue.textProperty().bind(modelTest.handValueProperty());


        //using buttons for test
        //test for changeFaceUp
        // stay.setOnAction(e-> modelTest.activePlayerHandArr.get(0).changeFace());

        //testing hit
        //hit.setOnAction(e->modelTest.hitListener());
        hit.setOnAction(e -> BlackJackLogic.actionQueue.add(1));
        end.setOnAction(e -> player2.getChildren().add(new CardGraph("clubs", "ace", true)));
        stay.setOnAction(e -> BlackJackLogic.actionQueue.add(0));
        rules.setOnAction(e ->rulesPanel.setVisible(true));
        rulesPanel.setOnMouseClicked(e -> rulesPanel.setVisible(false));//maybe change to setOnAction if hovering breaks things

    }

    public void setListener(ObservableList<Card> observable, HBox playerBox) {
        observable.addListener((ListChangeListener<Card>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    CardGraph c = cardToGraph(change.getAddedSubList().get(0));
                    //  CardGraph c=change.getAddedSubList().get(0);
                    if (observable.size() > 1)
                        c.setTranslateX(-(50 * (observable.size() - 1)));//this has to be simplified
                    playerBox.getChildren().add(c);

                } else if (change.wasRemoved()) {
                    playerBox.getChildren().clear();
                }

                /*
                else if(change.wasUpdated()) {
                    System.out.println(change.getList().toString());
                    ((CardGraph) playerBox.getChildren().get(change.getFrom())).changeFace();
                }

                 */
            }
        });
    }

    public void setButtonListener(){
        modelTest.disableButtons.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                hit.setDisable(newValue);
                stay.setDisable(newValue);
            }
        });
    }

    public CardGraph cardToGraph(Card card) {
        String rank = String.valueOf(card.getRank());
        String suit = String.valueOf(card.getSuit()).toLowerCase();
        switch (rank) {
            case "0" -> rank = "ace";
            case "1" -> rank = "ace";
            case "11" -> rank = "jack";
            case "12" -> rank = "queen";
            case "13" -> rank = "king";
            default -> rank = rank;
        }
        return new CardGraph(suit, rank, true);
    }
}
