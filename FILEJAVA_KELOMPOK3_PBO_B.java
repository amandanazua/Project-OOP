package Playlist;

import java.util.*;

public class Playlist {
    static int pointer = 0;
    static Scanner in = new Scanner(System.in);

    static ArrayList<Music> playlist = new  ArrayList<Music>();
    static String sort = "Recently added";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\033[0;33m";

    public static void main(String[] args) {
        System.out.println(ANSI_YELLOW+"\n\t\t\t\t\t\t\t\t\t\tWELCOME TO\n");
        System.out.println(
                "██████╗░  ███████╗  ███╗░░██╗  ██████╗░  ░█████╗░  ███╗░░██╗  ░██████╗░  ██╗  ███╗░░██╗\n"+
                "██╔══██╗  ██╔════╝  ████╗░██║  ██╔══██╗  ██╔══██╗  ████╗░██║  ██╔════╝░  ██║  ████╗░██║\n" +
                "██║░░██║  █████╗░░  ██╔██╗██║  ██║░░██║  ███████║  ██╔██╗██║  ██║░░██╗░  ██║  ██╔██╗██║\n" +
                "██║░░██║  ██╔══╝░░  ██║╚████║  ██║░░██║  ██╔══██║  ██║╚████║  ██║░░╚██╗  ██║  ██║╚████║\n" +
                "██████╔╝  ███████╗  ██║░╚███║  ██████╔╝  ██║░░██║  ██║░╚███║  ╚██████╔╝  ██║  ██║░╚███║\n" +
                "╚═════╝░  ╚══════╝  ╚═╝░░╚══╝  ╚═════╝░  ╚═╝░░╚═╝  ╚═╝░░╚══╝  ░╚═════╝░  ╚═╝  ╚═╝░░╚══╝"+ANSI_RESET +
                "\n");
        System.out.println("");
        String lanjut = "Y";
        while(lanjut.equals("Y")){
            addMusic();
            System.out.println("");
            if(playlist.size() != 0){
                System.out.print("Want to add another music?\n" +
                        "[Y] Continue\n" +
                        "[Click anything] Stop\n" );
            }
            while(playlist.size() == 0){
                System.out.println("Oops! You haven't added a song yet.");
                addMusic();
                System.out.println("");
            }
            lanjut = in.nextLine();
            System.out.println("");
        }

        System.out.println(ANSI_YELLOW + "\t\t\t\t\t\t\t\t  DENDANGIN IS READY!\n");
        System.out.println(ANSI_YELLOW+
                        "██████╗░  ███████╗  ███╗░░██╗  ██████╗░  ░█████╗░  ███╗░░██╗  ░██████╗░  ██╗  ███╗░░██╗\n"+
                        "██╔══██╗  ██╔════╝  ████╗░██║  ██╔══██╗  ██╔══██╗  ████╗░██║  ██╔════╝░  ██║  ████╗░██║\n" +
                        "██║░░██║  █████╗░░  ██╔██╗██║  ██║░░██║  ███████║  ██╔██╗██║  ██║░░██╗░  ██║  ██╔██╗██║\n" +
                        "██║░░██║  ██╔══╝░░  ██║╚████║  ██║░░██║  ██╔══██║  ██║╚████║  ██║░░╚██╗  ██║  ██║╚████║\n" +
                        "██████╔╝  ███████╗  ██║░╚███║  ██████╔╝  ██║░░██║  ██║░╚███║  ╚██████╔╝  ██║  ██║░╚███║\n" +
                        "╚═════╝░  ╚══════╝  ╚═╝░░╚══╝  ╚═════╝░  ╚═╝░░╚═╝  ╚═╝░░╚══╝  ░╚═════╝░  ╚═╝  ╚═╝░░╚══╝"
                        );

        int command = 0;
        while (true){
            display();
            System.out.print("Command (0 for exit): ");
            try{
                command = Integer.parseInt(in.nextLine());
            }catch(NumberFormatException e){
                System.out.println("Sorry, the command you entered is wrong!");
                continue;
            }
            if(command == 1){
                nextMusic();
            }
            else if(command == 2){
                prevMusic();
            }
            else if(command == 3){
                if(playlist.get(pointer).getFavorit() == false){
                    playlist.get(pointer).makeFav();
                }else{
                    playlist.get(pointer).deleteFav();
                }
            }
            else if(command == 4){
                System.out.println("");
                detailsCurrentMusic();
            }
            else if(command == 5){
                System.out.println("");
                deleteCurrentMusic();
            }
            else if(command == 6){
                System.out.println("");
                searchMusic();
            }
            else if(command == 7){
                System.out.println("");
                addMusic();
            }
            else if(command == 8){
                sortMusic();
            }
            else if(command == 9){
                System.out.println("");
                for(int i = 0; i < playlist.size(); i++){
                    System.out.println("Music "+ (i+1));
                    System.out.println(playlist.get(i));
                    System.out.println("");
                }
            }
            else if(command == 0){
                break;
            }
            else {
                System.out.println("Sorry, the command you entered is wrong!");
            }
        }

        System.out.println("Thank you for using Dendangin!");
    }
    private static void searchMusic(){

        System.out.print("Search Music\n");
        System.out.print("Title  : ");
        String judul = in.nextLine();
        System.out.print("Artist : ");
        String artist = in.nextLine();

        Music details = findMusic(judul, artist);

        if(details  != null){
            System.out.println("\n-----------------------------");
            System.out.println("Title : " + details.getJudul());
            System.out.println("Artist: " + details.getArtis());
            System.out.println("Album : " + details.getAlbum());
            System.out.println("Year  : " + details.getTahun());
            System.out.println("-----------------------------");
        } else{
            System.out.println("Music not found.");
            return;
        }

        System.out.println("\n[1] Delete Music");
        System.out.println("[2] Play Music");
        if(details.getFavorit() == false)  System.out.println("[3] Make Favorite");
        else System.out.println("[3] Unfavorite");
        System.out.println("[0] Back To Main Menu");

        System.out.print("Command: ");
        String command = in.nextLine();

        if(command.equals("1")){
            if(playlist.size() == 1){
                System.out.println("Oops! You haven't added a song yet.");
            }else{
                System.out.println("Music deleted succesfully!");
                playlist.remove(details);
            }
            if(pointer == playlist.size()) pointer = 0;
        }else if(command.equals("2")){
            pointer = findMusicINT(judul, artist);
        }else if(command.equals("3")){
            if(details.getFavorit() == false) {
                System.out.println("The music succesfully added as a favorite!");
                details.makeFav();
            } else {
                System.out.println("The music succesfully deleted from favorite");
                details.deleteFav();
            }
        }else if(command.equals("0")){
            return;
        }else{
            System.out.println("Wrong command :(");
            return;
        }
    }

    private static void nextMusic() {

        if(pointer+1 == playlist.size()){
            pointer = 0;
        }
        else{
            pointer++;
        }

    }


    private static void deleteCurrentMusic() {
        if(playlist.size() == 1){
            System.out.println("Failed to delete music.");
        }
        else{

            playlist.remove(pointer);
            if(pointer == playlist.size()) pointer = 0;
            System.out.println("Music succesfully deleted.");

        }
    }


    private static void detailsCurrentMusic() {
        Music details = playlist.get(pointer);
        System.out.println("Music Data:");
        System.out.println("Title : " + details.getJudul());
        System.out.println("Artist: " + details.getArtis());
        System.out.println("Album : " + details.getAlbum());
        System.out.println("Year  : " + details.getTahun());
    }


    private static Music findMusic(String judul, String artist) {
        for(int i = 0; i < playlist.size(); i++){
            if(playlist.get(i).getJudul().equals(judul) && playlist.get(i).getArtis().equals(artist)){
                return playlist.get(i);
            }
        }
        return null;
    }

    private static int findMusicINT(String judul, String artist) {
        for(int i = 0; i < playlist.size(); i++){
            if(playlist.get(i).getJudul().equals(judul) && playlist.get(i).getArtis().equals(artist)){
                return i;
            }
        }
        return -1;
    }

    private static void prevMusic() {
        if(pointer == 0){
            pointer = playlist.size()-1;
        }
        else{
            pointer--;
        }

    }

    private static void addMusic() {

        System.out.println("Please enter your music!");
        System.out.print("Title : ");
        String judul = in.nextLine();
        System.out.print("Artist: ");
        String artist = in.nextLine();
        System.out.print("Album : ");
        String album = in.nextLine();
        System.out.print("Year  : ");
        String tahun = in.nextLine();

        try{
            playlist.add(new Music(judul, artist, album, Integer.parseInt(tahun)));
        }catch(NumberFormatException e){
            System.out.println("Failed to add music!");
        }

    }


    private static void display() {
        String displayedMusic = (playlist.get(pointer).getFavorit() == true) ? ANSI_CYAN+"* "+playlist.get(pointer).getJudul()+" - "
                +playlist.get(pointer).getArtis() + " *"+ANSI_RESET : ANSI_GREEN+playlist.get(pointer).getJudul()+" - "
                +playlist.get(pointer).getArtis()+ANSI_RESET;
        System.out.println();

        int width = 103;
        String s = displayedMusic;

        int padSize = width - s.length();
        int padStart = s.length() + padSize / 2;

        s = String.format("%" + padStart + "s", s);
        s = String.format("%-" + width  + "s", s);

        System.out.println(ANSI_RESET + new String(new char[96]).replace( "\0", "="));
        System.out.println("\t\t\t\t\t\t\t\t\t  Currently Playing");
        System.out.println(s);
        System.out.println("\t\t\t\t\t\t\t\t  00:01 ━⬤─────────── 04:05 ");
        System.out.println("\t\t\t\t\t\t\t\t\t ⇆ㅤ ||◁ㅤ❚❚ㅤ▷||ㅤ ↻  ");
        System.out.println(new String(new char[96]).replace("\0", "="));
        System.out.println(" ");

        System.out.println("Playlist sorted by: "+ sort + "\n");

        System.out.println("[1] Next Music\t\t\t\t" + "  [6] Search Music");
        System.out.println("[2] Previous Music\t\t\t" + "  [7] Add Music");
        if(playlist.get(pointer).getFavorit() == true) System.out.println("[3] Unfavorite\t\t\t\t" + "  [8] Sort Playlist");
        else System.out.println("[3] Make Favorite\t\t\t" + "  [8] Sort Playlist");
        System.out.println("[4] Details Current Music\t" + "  [9] Show All Music");
        System.out.println("[5] Delete Current Music");
    }

    private static void sortMusic(){
        System.out.println("");
        System.out.println("Sort playlist by: ");
        System.out.println("[1] Favorite");
        System.out.println("[2] Title");
        System.out.println("[3] Artist");
        System.out.println("[4] Album");
        System.out.println("[5] Year");
        System.out.println("[6] Recently added");
        System.out.println("[7] Back to menu");
        System.out.print("Command : ");
        String command = in.nextLine();
        if(command.equals("1")) {
            Collections.sort(playlist, new SortByFavorit());
            sort = "Favorite";
        }
        else if(command.equals("2")) {
            Collections.sort(playlist, new SortByJudul());
            sort = "Title";
        }
        else if(command.equals("3")) {
            Collections.sort(playlist, new SortByArtis());
            sort = "Artis";
        }
        else if(command.equals("4")) {
            Collections.sort(playlist, new SortByAlbum());
            sort = "Album";
        }
        else if(command.equals("5")) {
            Collections.sort(playlist, new SortByTahun());
            sort = "Year";
        }
        else if(command.equals("6")) {
            Collections.sort(playlist);
            sort = "Recently added";
        }
        else if(command.equals("7")) {
            return;
        }
        else {
            System.out.println("Wrong Command");
            return;
        }
        pointer = 0;
        System.out.println("Managed to sort by : "+ sort);
        return;
    }

}

class Music implements Comparable<Music>{

    private String judul;
    private String artis;
    private String album;
    private int tahun;
    private boolean favorit;
    private static int howMuchMusic = 0;
    private int added;

    Music(){

    }

    Music(String judul, String artis, String album, int tahun){
        this.judul = judul;
        this.artis = artis;
        this.album = album;
        this.tahun = tahun;
        howMuchMusic++;
        added = howMuchMusic;
        favorit = false;
    }

    public void makeFav(){
        favorit = true;
    }

    public void deleteFav(){
        favorit = false;
    }

    public String getAlbum() {
        return album;
    }

    public boolean getFavorit(){
        return favorit;
    }

    public String getArtis() {
        return artis;
    }

    public String getJudul() {
        return judul;
    }

    public int getTahun() {
        return tahun;
    }

    public int getAdded() {
        return added;
    }

    public int compareTo(Music other){
        if(this.getAdded() < other.getAdded()){
            return 1;
        }else{
            return -1;
        }
    }

    public String toString(){
        String temp = "-";
        if(favorit == true){
            temp = "Favorite";
        }
        return "Title : " + judul + "\n" + "Artist : " + artis + "\n" + "Album : " + album
                + "\n" + "Year : " + tahun + "\n" + "Favorite : " + temp;
    }
}

class SortByJudul implements Comparator<Music>{

    public int compare(Music a, Music b){
        if(a.getJudul().compareTo(b.getJudul()) > 0){
            return 1;
        }else if(a.getJudul().compareTo(b.getJudul()) < 0){
            return -1;
        }else{
            if(a.getAdded() < b.getAdded()){
                return 1;
            }else{
                return -1;
            }
        }
    }

}

class SortByArtis implements Comparator<Music>{

    public int compare(Music a, Music b){
        if(a.getArtis().compareTo(b.getArtis()) > 0){
            return 1;
        }else if(a.getArtis().compareTo(b.getArtis()) < 0){
            return -1;
        }else{
            if(a.getAdded() < b.getAdded()){
                return 1;
            }else{
                return -1;
            }
        }
    }

}

class SortByFavorit implements Comparator<Music>{
    public int compare(Music a, Music b){
        if(a.getFavorit() == true && b.getFavorit() == false){
            return -1;
        }else if(a.getFavorit() == false && b.getFavorit() == true){
            return 1;
        }else{
            if(a.getAdded() < b.getAdded()){
                return -1;
            }else{
                return 1;
            }
        }
    }
}

class SortByAlbum implements Comparator<Music>{
    public int compare(Music a, Music b){
        if(a.getAlbum().compareTo(b.getAlbum()) > 0){
            return 1;
        }else if(a.getAlbum().compareTo(b.getAlbum()) < 0){
            return -1;
        }else{
            if(a.getAdded() < b.getAdded()){
                return 1;
            }else{
                return -1;
            }
        }
    }
}

class SortByTahun implements Comparator<Music>{
    public int compare(Music a, Music b){
        if(a.getTahun() > b.getTahun()){
            return 1;
        }else if(a.getTahun() < b.getTahun()){
            return -1;
        }else{
            if(a.getAdded() < b.getAdded()){
                return 1;
            }else{
                return -1;
            }
        }
    }
}