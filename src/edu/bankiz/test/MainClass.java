package edu.bankiz.test;


import edu.bankiz.entities.Reclamation;
import edu.bankiz.entities.Utilisateur;
import edu.bankiz.services.CompteController;
import edu.bankiz.services.ReclamationCRUD;
import edu.bankiz.services.TransactionController;
import edu.bankiz.services.UtilisateurCRUD;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
//ajouter utilisateur
        /*Utilisateur u = new Utilisateur(22,"yoser","yoyo",new Date(2017,10,22),"yoser@esprit.tn",123333,"admin","yoseryoser");
        UtilisateurCRUD pcd = new UtilisateurCRUD();
        pcd.ajouterUtilisateur(u);*/
//afficher utilisateurs
        //UtilisateurCRUD pcd = new UtilisateurCRUD();
        //System.out.println(pcd.afficherUtilisateur());
//modifier utilisateur
        /*Utilisateur u = new Utilisateur(333,"sana","sana",new Date(2017,10,22),"sana@esprit.tn",123333,"admin","yoseryoser");
        UtilisateurCRUD pcd = new UtilisateurCRUD();
        pcd.modifierUtlisateur(u,22);*/
//supprimer utilisateur
        /*UtilisateurCRUD pcd = new UtilisateurCRUD();
        pcd.supprimerUtilisateur(45);*/
//afficher tri utilisateurs par nom
        /*UtilisateurCRUD pcd = new UtilisateurCRUD();
        System.out.println(pcd.triUtilisateurParNom());*/
//login
       /* UtilisateurCRUD pcd = new UtilisateurCRUD();
        pcd.login("yoser.walha@esprit.tn","yoseryoser");*/

//ajouter reclamation
        /*Reclamation r = new Reclamation(44,"carte",dtf.format(now),"perdu","grave");
        ReclamationCRUD pcd = new ReclamationCRUD();
        pcd.ajouterReclamation(r);*/
//nombre de recamation
        /*ReclamationCRUD pcd = new ReclamationCRUD();
        System.out.println(pcd.nbReclamation());*/
//afficher reclamarions
        /*ReclamationCRUD pcd = new ReclamationCRUD();
        System.out.println(pcd.afficherReclamation());  */
//modifier reclamation
       /* Reclamation r = new Reclamation(23,"carte",new Date(2017,10,22),"vite","bien");
        ReclamationCRUD pcd = new ReclamationCRUD();
        pcd.modifierReclamation(r,7);*/
//supprimer reclamation
        /*ReclamationCRUD pcd = new ReclamationCRUD();
        pcd.supprimerReclamation(7);*/
//email Controle de saise
        /*UtilisateurCRUD pcd = new UtilisateurCRUD();
        System.out.println(pcd.email_Validation("yoseresprit.tn"));*/
//verifierPwd pour confirmer mot de passe
        /*UtilisateurCRUD pcd = new UtilisateurCRUD();
        System.out.println(pcd.verifierPwd("nour"));*/
//modifierPassword
        /*UtilisateurCRUD pcd = new UtilisateurCRUD();
        pcd.modifierPassword("nour@nour.nour","nour");*/
//nbAdmins
        /*UtilisateurCRUD pcd = new UtilisateurCRUD();
        System.out.println(pcd.nbAdmins());*/
//afficher mes  reclamarions
        /*ReclamationCRUD pcd = new ReclamationCRUD();
        System.out.println(pcd.afficherMesReclamations(44));*/
//calcul age
        /*UtilisateurCRUD pcd = new UtilisateurCRUD();
        int age = pcd.calculateAge(new Date(1999,07,05));
        System.out.println(age);*/

//findByPrenom
        /*UtilisateurCRUD pcd = new UtilisateurCRUD();
        pcd.findByPrenom("oo");*/
        UtilisateurCRUD pcd = new UtilisateurCRUD();
        //pcd.findByMail("yoser.walha@esprit.tn");
        pcd.verifierEmailBd("yoser.walha@esprit.tn");
        //-------------------------------------- PARTIE COMPTE ---------------------------------------------------
                //CompteController comptCont = new CompteController();

        //        ***************************** Ajouter un compte *******************************
//        long numCompte = comptCont.GenererNumCompte();
//        Compte cmpt = new Compte(numCompte, "123451234" +numCompte+"12", 1234444, dtf.format(now), "Courant", 123, 0.07f, 13, 1);
//        comptCont.ajouterCompte2(cmpt);

//        ***************************** Afficher les comptes *******************************
//        System.out.println(comptCont.afficherComptes());

        //        ***************************** Afficher Mon compte *******************************
//        Scanner scanner = new Scanner( System.in );
//        System.out.print( "Veuillez saisir l'id du client : " );
//        int id = scanner.nextInt();
//        Compte compte = comptCont.afficherMonCompte(id);
//        if (compte.getNum_compte() == 0) {
//            System.out.println("Ce compte n'existe pas !");
//        } else {
//            System.out.println(compte);
//        }

//        ***************************** Supprimer un compte *******************************
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Veuillez saisir l'id du compte : ");
//        int idcmpt = scanner.nextInt();
//        Compte compte = comptCont.rechercherCompte(idcmpt);
//        if (compte.getNum_compte() == 0) {
//            System.out.println("Ce compte n'existe pas !");
//        } else {
//            comptCont.supprimerCompte(idcmpt);
//        }

//        ***************************** Modifier un compte *******************************
//        Scanner scanner = new Scanner( System.in );
//        System.out.print( "Veuillez saisir l'id du compte à modifier : " );
//        int idcmptmod = scanner.nextInt();
//        Compte modifie = comptCont.rechercherCompte(idcmptmod);
//        if (modifie.getNum_compte() == 0){
//            System.out.println("Ce compte n'existe pas !");
//        }else{
//            modifie.setEtat_compte(1);
//            modifie.setSolde_compte(11111);
//            comptCont.modifierCompte(modifie, idcmptmod);
//        }


//****************************************SANA*********************************************************************


        //        -------------------------------------- PARTIE TRANSACTIONS ---------------------------------------------------
        TransactionController transCont = new TransactionController();

        //        ***************************** Ajouter une transaction *******************************

//        Transaction trans = new Transaction(79, "123451234 40972116276 12", 1000, dtf.format(now), "cadeau", 56, "Nour Boughattas", "Virement", 1);
//        transCont.ajouterTransaction2(trans);
//        comptCont.modificationTransaction(trans.getRIB_emetteur(), trans.getRIB_recepteur(), trans.getMontant_transaction());

//        ***************************** Afficher les transactions *******************************
//        System.out.println(transCont.afficherTransactions());

        //        ***************************** Afficher Mes transactions *******************************
 //       Scanner scanner = new Scanner( System.in );
//        System.out.print( "Veuillez saisir l'id du compte emetteur : " );
//        int idtrans = scanner.nextInt();
//        System.out.println(transCont.afficherMesTransactions(idtrans));

        //        ***************************** Supprimer une transaction *******************************
//        Scanner scanner = new Scanner( System.in );
//        System.out.print( "Veuillez saisir l'id de la transaction : " );
//        int idtranss = scanner.nextInt();
//        Transaction transaction = transCont.rechercherTransaction(idtranss);
//        if (transaction.getRIB_recepteur() == null){
//            System.out.println("Cette transaction n'existe pas !");
//        }else{
//            transCont.supprimerTransaction(idtranss);
//        }

//                *****************************Modifier une transaction *******************************
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Veuillez saisir l'id de la transaction à modifier : ");
//        int idtransmod = scanner.nextInt();
//        Transaction modifie = transCont.rechercherTransaction(idtransmod);
//        if (modifie.getRIB_recepteur() == null) {
//            System.out.println("Cette transaction n'existe pas !");
//        } else {
//            modifie.setType_transaction("Encaissement");
//            modifie.setMontant_transaction(11111);
//            transCont.modifierTransaction(modifie, idtransmod);
//        }
    }
}
