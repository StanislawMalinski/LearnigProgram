package BackEnd.Stats;

import java.io.File;

import BackEnd.Material;


public class StatsManager {
    private File file;
    private Material material;

    public StatsManager(File file){
        this.file = file;
        this.material = null;
    }

    public StatsManager(Material material){
        this.file = null;
        this.material = material;
    }

    public void read(){

    }
}

/*
1) Czytam plik wejściowy
2) Sprawdzamy czy isteniej dla tego pliku plik z statystyką
3.a) Jeżeli nie to tworzymy oraz otwieramy go
3.b) Jeżeli tak to otwieramy go
4.b) Porównujemy plik wejściowy z plikiem z statyską
5.b) W razie potrzeby aktualizujemy go
6) Wczytujemy pytania i tworzymy nowy instancje klasy material zawierające dane z pliku
 */
