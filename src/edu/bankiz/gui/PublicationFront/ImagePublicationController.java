package edu.bankiz.gui.PublicationFront;

import edu.bankiz.entities.Commentaire;
import edu.bankiz.entities.Publication;
import edu.bankiz.entities.post_like;
import edu.bankiz.services.LikesController;
import edu.bankiz.services.PublicationController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ImagePublicationController implements Initializable {
    Commentaire pub = new Commentaire();
    @FXML
    private Button btnCommentaire;
    @FXML
    private Button btnLike;
    @FXML
    private Label numberOfLikes;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    private Publication p;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private ImageView image;
    @FXML
    private Button modifBtn;
    @FXML
    private Label title;
    @FXML
    private Label category;
    @FXML
    private Button deleteBtn;

    /*@FXML
    public void supprimerPublication(Event event) {
        PublicationController ps=new PublicationController() ;
        List<Publication> publications=ps.afficherPublications();
        for(int i=0;i<publications.size();i++){
            int j=i;
            deleteBtn.setOnAction( new EventHandler<ActionEvent>(){
                @Override public void handle(ActionEvent e){

                    PublicationController pans=new PublicationController();
                    pans.supprimerPublication(publications.get(j).getId());

                    pans.afficherPublications();
                }});
        }
    } */
    @FXML
    public void supprimerPublication(Event event) {
        PublicationFrontController pfc = new PublicationFrontController();
        PublicationController ps = new PublicationController();
        List<Publication> publications = ps.afficherPublications();
        for (int i = 0; i < publications.size(); i++) {
            int j = i;
            deleteBtn.setOnAction( new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {

                    PublicationController pans = new PublicationController();
                    pans.supprimerPublication(publications.get(j).getId());
                    /*FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("PublicationFront.fxml"));*/

                    try {
                        ((Node)(e.getSource())).getScene().getWindow().hide();
                        Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/PublicationFront/PublicationFront.fxml"));
                        Scene scene = new Scene(root, 1000, 680);
                        scene.setFill(Color.TRANSPARENT);
                        Image icon = new Image(getClass().getResourceAsStream("../../../../Images/logo-Final.png"));
                        Stage stage = new Stage();
                        stage.getIcons().add(icon);
                        stage.setTitle("Les Articles");
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
    }

    @FXML
    public void modifierPublication(Event event) {
    }
    @Deprecated
    public void setData (Publication pub) {
        this.p= pub;
        title.setText(pub.getTitre_publication());
        category.setText(pub.getCategorie_publication());
        File f = new File(p.getImage_publication());
        Image i = new Image(f.toURI().toString());
        image.setImage(i);
        image.setPreserveRatio(true);
        image.fitWidthProperty().bind(rootPane.widthProperty());
        image.fitHeightProperty().bind(rootPane.heightProperty());
    }

    @FXML
    public void listeCommentaire(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/CommentaireFront/CommentaireFront.fxml"));
            Scene scene = new Scene(root, 1000, 680);
            scene.setFill(Color.TRANSPARENT);
            Image icon = new Image(getClass().getResourceAsStream("../../../../Images/logo-Final.png"));
            Stage stage = new Stage();
            stage.getIcons().add(icon);
            stage.setTitle("Les Articles");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void clickLike(MouseEvent event) {
        Publication pub = new Publication();
        int id = 0;
        System.out.println(id);
        LikesController pl = new LikesController();
        post_like p = new post_like();
        int likes = pl.countLikePost(p);
        System.out.println(likes);
        System.out.println(p);
        likes = likes +1;
        System.out.println(likes);
        numberOfLikes.setText(String.valueOf(likes));
    }
}
