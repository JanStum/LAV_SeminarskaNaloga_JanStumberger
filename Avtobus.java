import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Avtobus{
    protected String imeAvtobusa;
    protected String imeVoznika;
    protected String[] postaja = new String[15];
    protected String[] dneviVoznje = new String[7];
    protected int[] vozniRedUre = new int[15];
    protected int[] vozniRedMinute = new int[15];
    protected int steviloPolnihPostaj;
    protected int steviloPolnihDniVTednu;
    static int steviloAvtobusov = 0;



    // Konstruktor brez parametrov, ki v vse lastnosti avtobusa vpiše presledke, če je podatek tipa String in 0, če je podatek tipa int 
    public Avtobus(){
        imeAvtobusa = " ";
        imeVoznika = " ";
        steviloPolnihPostaj = 0;
        steviloPolnihDniVTednu = 0;
        for (int i=0; i<7; i++){
            dneviVoznje[i] = " ";
        }
        for (int i=0; i<15; i++){
            postaja[i] = " ";
            vozniRedUre[i] = 0;
            vozniRedMinute[i] = 0;
        }
    }
    public void setImeAvtobusa(String ia){
        imeAvtobusa = ia;
    }
    public void setImeVoznika(String iv){
        imeVoznika = iv;
    }
    public void setPostaja(String[] p){ postaja = Arrays.copyOf(p, p.length); }
    public void setDneviVoznje(String[] dv){
        dneviVoznje = Arrays.copyOf(dv, dv.length);
    }
    public void setVozniRedUre(int[] vru){ vozniRedUre = Arrays.copyOf(vru, vru.length); }
    public void setVozniRedMinute(int[] vrm){
        vozniRedMinute = Arrays.copyOf(vrm, vrm.length);
    }
    public void setSteviloPolnihPostaj(int spp){ steviloPolnihPostaj = spp; }
    public void setSteviloPolnihDniVTednu(int spdvt){
        steviloPolnihDniVTednu = spdvt;
    }
    public String getImeAvtobusa(){
        return imeAvtobusa;
    }
    // Metoda primerja, če je postaja, vnešena v parametrih metode, enaka eni izmed postaj instance razreda Avtobus, preko katere kliče to metodo
    // Metoda sprejme parameter, ki predstavlja postajo, s katero želimo primerjati postaje instance razreda Avtobus
    // Metoda vrne true, če taka postaja obstaja in false, če ne.
    public boolean primerjajPostajo(String p){
        boolean primerjava = true;
        for (int i=0; i<15; i++){
            if (primerjava == postaja[i].equals(p))
                return true;
        }
        return false;
    }

    // Metoda, ki primerja, če vpisani dan vožnje enak kot ta, ki ga je zapisal uporabnik
    // Metoda sprejme parameter, ki določa dan vožnje in je tipa String
    // Metoda vrne true, če je parameter enak enemu od dni vožne na keterega avtobus vozi in false, če ni
    public boolean primerjajDneveVoznje(String dv){
        for (int i=0; i<dneviVoznje.length; i++){
            if (dneviVoznje[i].equals(dv))
                return true;
        }
        return false;
    }
    // Metoda za izpis voznega reda avtobusa
    // Vhodna podatka sta vstopna in izstopna postaja tipa String
    // Metoda ne vrne vračane vrednosti
    public void izpisVoznegaReda(String vstopnaPostaja, String izstopnaPostaja){
        for (int i=0; i<steviloPolnihPostaj; i++){
            if (postaja[i].equals(vstopnaPostaja) || postaja[i].equals(izstopnaPostaja))
                System.out.println("    Postaja: "+ postaja[i]+",   prihod: "+vozniRedUre[i]+"h"+vozniRedMinute[i]+"min");
            else
                System.out.println("Postaja: "+ postaja[i]+",   prihod: "+vozniRedUre[i]+"h"+vozniRedMinute[i]+"min");
        }
    }
    // Metoda za izračun časa vožnje, glede na uporabnikov vpis vstopne in izstopne postaje
    // Vhodna podatka sta vstopna in izstopna postaja
    // Izhodni podatek je čas vožnje tipa String
    public String razlikaCasa(String vstopnaPostaja, String izstopnaPostaja){
        int zmanjsevanec = 0; int odstevanec = 0;
        int razlika, razlikaUr, razlikaMinut;
        for (int i=0; i<15; i++){
            if (postaja[i].equals(vstopnaPostaja)){
                odstevanec = vozniRedUre[i]*60 + vozniRedMinute[i];
                break;
            }
        }
        for (int i=0; i<15; i++){
            if (postaja[i].equals(izstopnaPostaja)){
                zmanjsevanec = vozniRedUre[i]*60 + vozniRedMinute[i];
                break;
            }
        }
        razlika = zmanjsevanec - odstevanec;
        razlikaUr = razlika/60;
        razlikaMinut = razlika - razlikaUr*60;
        return Integer.toString(razlikaUr)+"h"+Integer.toString(razlikaMinut)+"min";
    }
    // Metoda za izpis vseh podatkov avtobusa
    // Metoda nima vhodnih podatkov
    // Metoda nima izhodnih podatkov
    public void izpisVsehPodatkov(){
        String dv = dneviVoznje[0];
        System.out.println("Ime avtobusa: "+ imeAvtobusa);
        System.out.println("Ime voznika: "+ imeVoznika);
        for (int i=1; i<steviloPolnihDniVTednu; i++){
            dv = dv + ", " + dneviVoznje[i];
        }
        System.out.println("Dnevi vožnje v tednu: "+ dv);
        for (int i=0; i<steviloPolnihPostaj; i++){
            System.out.println("postaja: "+ postaja[i]+",   prihod: "+vozniRedUre[i]+"h"+vozniRedMinute[i]+"min");
        }
        System.out.println();
    }
    // Metoda, ki vrne String vseh lastnosti izbranega avtobusa
    // Metoda vrne vrednost tipa string
    // Metoda ima prazen seznam parametrov
    @Override
    public String toString() {
        return "IZPIS Avtobusa " + imeAvtobusa + " (voznik: " + imeVoznika + "): postaja="
                + Arrays.toString(postaja) + ", dneviVoznje=" + Arrays.toString(dneviVoznje) + ", vozniRedUre="
                + Arrays.toString(vozniRedUre) + ", vozniRedMinute=" + Arrays.toString(vozniRedMinute)
                + ", steviloPolnihPostaj=" + steviloPolnihPostaj + ", steviloPolnihDniVTednu=" + steviloPolnihDniVTednu
                + "]";
    }
    // Metoda, ki iz instance razreda Bus naredi novo tekstovno datoteko, ki vsebuje vse lastnosti tega vozila, da jo lahko ob ponovnem zagonu programa pretvorimo nazaj v objekt
    // Metoda nima vračane vrednosti
    // Metoda ima prazen seznam parametrov
    public void ustvariTekstovnoDatotekoIzAvtobusa()throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("Bus" + steviloAvtobusov + ".txt"));
        steviloAvtobusov ++;

        bw.write(imeAvtobusa + "\n");
        bw.write(imeVoznika + "\n");
        for (int i = 0; i < dneviVoznje.length; i++)
            bw.write(dneviVoznje[i] + " ");
        bw.write("\n");
        for (int i = 0; i < postaja.length; i++){
            bw.write(postaja[i].replace(" ", "#") + " ");
        }
        bw.write("\n");
        for (int i = 0; i < vozniRedUre.length; i++)
            bw.write(vozniRedUre[i] + " ");
        bw.write("\n");
        for (int i = 0; i < vozniRedMinute.length; i++)
            bw.write(vozniRedMinute[i] + " ");
        bw.write("\n");
        bw.write(steviloPolnihDniVTednu + "\n");
        bw.write(steviloPolnihPostaj + "\n");
        bw.close();
    }
}