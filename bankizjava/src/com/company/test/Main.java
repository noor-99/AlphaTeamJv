package com.company.test;
import com.company.entities.Cheque;
import com.company.entities.Chequier;
import com.company.entities.Commentaire;
import com.company.entities.Publication;
import com.company.services.ChequeCrud;
import com.company.services.ChequierCrud;
import com.company.services.CommentaireCrud;
import com.company.services.PublicationCrud;
import com.company.utils.MyConnection;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class Main {

    public static void main(String[] args) {


        //ChequierCrud cc=new ChequierCrud();
        //cc.listerChequier();

// pour tester
        //  ChequierCrud cc=new ChequierCrud();

        // Chequier c =new Chequier();
        // c.setId(2);
        //c.setClient_tel(25362599);
        //  c.setMotif_chequier(123);
        //c.setEtat_chequier(1);
        // c.setNum_compte_id(789456123);
        // cc.supprimerChequier(c);
        //  cc.listerChequier();

        ChequeCrud cc = new ChequeCrud();
        Cheque c = new Cheque();
        Cheque c2 = new Cheque();

        c.setSignature(123);
        c.setClient_tel(79456);
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        c.setDate_cheque(date);
        c.setDestinataire_id(1);
        c.setLieu("marsa");
        c.setMontant(200);
        c.setRib_reciever("78945632");
        c.setRib_sender("1254729");
        c.setIdchequiers_id(111);
        c.setProprietaire_id(5);
        //cc.ajouterCheque(c);
        c2.setId(15);
        c2.setClient_tel(963);
        c2.setLieu("sidi bou");
        c2.setMontant(788);
        cc.UpdateCheque(c2);
       /* @FXML
        private Boolean testNom() {
            int nbNonChar = 0;
            for (int i = 1; i < NomTXFLD.getText().trim().length(); i++) {
                char ch = NomTXFLD.getText().charAt(i);
                if (!Character.isLetter(ch)) {
                    nbNonChar++;
                }
            }

            if (nbNonChar == 0 && NomTXFLD.getText().trim().length() >= 3) {
                nomCheckMark.setImage(new Image("Images/checkMark.png"));
                return true;
            } else {
                nomCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
                return false;

            }

        }

    }*/
    }
}
