import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class AplikacijaZaAvtobus{
    static Avtobus bus[] = new Avtobus[15];
    public static void main(String[]args)throws IOException{
        BufferedReader vh = new BufferedReader(new InputStreamReader(System.in));
        int n = 0; int steviloPolnihPostaj = 0; boolean vstop = true;

        // Ustavari se 15 praznih instanc razreda Avtobus
        for (int i=0; i<15; i++){
            bus[i] = new Avtobus();
        }
        // Ustvarijo se objekti tipa Avtobus, katerih podatki so shranjeni v tekstovni datoteki. Ustvari se toliko avtobusov, kolikor je tekstovnih datotek v mapi.
        ustvariAvtobusIzTekstovneDatoteke();

        while (n!=3){
            // Prijava kot uporabnik ali upravitelj strani ali končanje programa.
            System.out.println("Izberite kot kdo se želite prijaviti (vpišite število): ");
            System.out.println("1. Uporabnik");
            System.out.println("2. Upravitelj strani");
            System.out.println("3. Konec programa");
            n = Integer.parseInt(vh.readLine());
            System.out.println();
            if (n==1){
                // Prijava kot uporabnik in vpis vstopne ter izstopne postaje in dneva v tednu
                System.out.print("Vstopna postaja: ");
                String vstopnaPostaja = vh.readLine();
                System.out.print("Izstopna postaja: ");
                String izstopnaPostaja = vh.readLine();
                System.out.print("Dan v tednu: ");
                String danVoznje = vh.readLine();
                // Zanka, ki izračuna določi, če se podatki, ki smo jih vpisali ujemajo kateremu avtobusu, nato pa izpiše vse podatke tega avtobusa
                for(int i=0; i<15; i++){
                    if (bus[i].primerjajDneveVoznje(danVoznje) && bus[i].primerjajPostajo(vstopnaPostaja) && bus[i].primerjajPostajo(izstopnaPostaja)){
                        System.out.println("Ime avtobusa: "+ bus[i].getImeAvtobusa());
                        System.out.println("Čas vožnje: "+ bus[i].razlikaCasa(vstopnaPostaja, izstopnaPostaja));
                        bus[i].izpisVoznegaReda(vstopnaPostaja, izstopnaPostaja);
                        break;
                    }
                    if(i==14)
                        System.out.println("Tak avtobus ne obstaja!");
                }
                System.out.println();
            }
            else if (n==2){
                // Prijava kot upravitelj strani
                while (n!=4){
                    if (vstop){
                        // Ta koda določa, če bo potrebno geslo za prijavo kot upravitelj ali ne, nato je treba vpisati geslo
                        System.out.print("Geslo: ");
                        String geslo = vh.readLine();
                        if (!geslo.equals("1234")){
                            System.out.println("Napačno geslo!");
                            break;
                        }
                    }
                    // Treba je izbrati funkcijo, ki jo lahko izvede upravitelj
                    System.out.println("Izberite funkcijo (vpišite število): ");
                    System.out.println("1. Ustvari nov avtobus");
                    System.out.println("2. Izbriši avtobus");
                    System.out.println("3. Izpiši podatke avtobusa");
                    System.out.println("4. Nazaj");
                    n = Integer.parseInt(vh.readLine());
                    System.out.println();
                    if (n==1){
                        while (n!=5){
                            // Ustvarjanje novega avtobusa, po ustvaritvi se število avtobusov poveča
                            System.out.println("Izberite funkcijo (vpišite število): ");
                            System.out.println("1. Nastavi ime avtobusa");
                            System.out.println("2. Nastavi ime voznika");
                            System.out.println("3. Nastavi dneve vožnje v tednu");
                            System.out.println("4. Nastavi postaje");
                            System.out.println("5. Nazaj");
                            n = Integer.parseInt(vh.readLine());
                            System.out.println();
                            if (n==1){
                                // Vpis imena novega avtobusa
                                System.out.print("Ime avtobusa: ");
                                String imeAvtobusa = vh.readLine();
                                bus[Avtobus.steviloAvtobusov].setImeAvtobusa(imeAvtobusa);
                            }
                            else if (n==2){
                                // Vpis imena voznika novega avtobusa
                                System.out.print("Ime voznika: ");
                                String imeVoznika = vh.readLine();
                                bus[Avtobus.steviloAvtobusov].setImeVoznika(imeVoznika);
                            }
                            else if (n==3){
                                // Vpis dnevov vožnje, na katere nov avtobus vozi v tednu
                                int steviloPolnihDniVTednu2 = 0;
                                String dneviVoznje[] = new String[7];
                                napolniArray(dneviVoznje);
                                for (int i=0; i<7; i++){
                                    System.out.print("Vpiši dan vožnje v tednu: ");
                                    dneviVoznje[i] = vh.readLine();
                                    steviloPolnihDniVTednu2 = i + 1;
                                    if (i<6){
                                        System.out.println("1. Vstavi nov dan v tednu");
                                        System.out.println("2. Nazaj");
                                        n = Integer.parseInt(vh.readLine());
                                        System.out.println();
                                        if (n==2){
                                            break;
                                        }
                                    }
                                }
                                bus[Avtobus.steviloAvtobusov].setDneviVoznje(dneviVoznje);
                                bus[Avtobus.steviloAvtobusov].setSteviloPolnihDniVTednu(steviloPolnihDniVTednu2);
                            }
                            else if (n==4){
                                // Vpis nove postaje novega avtobusa in čas prihoda avtobusa do te postaje
                                String[] postaja = new String[15];
                                int[] vozniRedUre = new int[15];
                                int[] vozniRedMinute = new int[15];
                                napolniArray(postaja);
                                napolniArray(vozniRedMinute);
                                napolniArray(vozniRedUre);

                                for (int i=0; i<15; i++){
                                    // Zanka za vpis novih avtobusov, ko vpišemo nove avtobuse, ki smo jih želeli lahko zapustimo zanko
                                    System.out.print("Vpiši ime postaje: ");
                                    postaja[i] = vh.readLine();
                                    System.out.print("Vpiši prihod do te postaje v urah: ");
                                    vozniRedUre[i] = Integer.parseInt(vh.readLine());
                                    System.out.print("Vpiši prihod do te postaje v minutah: ");
                                    vozniRedMinute[i] = Integer.parseInt(vh.readLine());
                                    System.out.println();
                                    bus[Avtobus.steviloAvtobusov].setSteviloPolnihPostaj(i+1);
                                    if (i<14){
                                        System.out.println("1. Vstavi novo postajo");
                                        System.out.println("2. Nazaj");
                                        n = Integer.parseInt(vh.readLine());
                                        System.out.println();
                                        if (n==2){
                                            break;
                                        }
                                    }
                                }
                                bus[Avtobus.steviloAvtobusov].setPostaja(postaja);
                                bus[Avtobus.steviloAvtobusov].setVozniRedUre(vozniRedUre);
                                bus[Avtobus.steviloAvtobusov].setVozniRedMinute(vozniRedMinute);
                            }
                            if (n==5){
                                bus[Avtobus.steviloAvtobusov].ustvariTekstovnoDatotekoIzAvtobusa();
                                n = 0;
                                vstop = false;
                                break;
                            }

                        }

                    }
                    else if (n==2){
                        // Izbris že obstoječega avtobusa, ko se izbere ime avtobusa za izbris, se izbriše vse njegove podatke, število avtobusov pa se zmanjša.
                        System.out.println("Kateri avtobus želite izbrisati (izberite število)?");
                        // Izpiše se seznam imen obstoječih avtobusov
                        for (int i=0; i<Avtobus.steviloAvtobusov; i++){
                            System.out.println(i+1+". "+ bus[i].getImeAvtobusa());
                        }
                        // Izberemo kateri avtobus želimo izbrisati
                        int indeksAvtobusa1 = Integer.parseInt(vh.readLine());
                        indeksAvtobusa1 -= 1;
                        System.out.println();
                        // Izbris vseh podatkov izbranega avtobusa
                        bus[indeksAvtobusa1].setImeAvtobusa(null);
                        bus[indeksAvtobusa1].setImeVoznika(null);
                        String[] postaja = new String[15];
                        bus[indeksAvtobusa1].setPostaja(postaja);
                        String[] dneviVoznje = new String[7];
                        bus[indeksAvtobusa1].setDneviVoznje(dneviVoznje);
                        int[] vozniRedMinute = new int[15];
                        bus[indeksAvtobusa1].setVozniRedMinute(vozniRedMinute);
                        int[] vozniRedUre = new int[15];
                        bus[indeksAvtobusa1].setVozniRedUre(vozniRedUre);
                        bus[indeksAvtobusa1].setSteviloPolnihPostaj(0);
                        bus[indeksAvtobusa1].setSteviloPolnihDniVTednu(0);
                        // Vsi avtobusi od izbrisanega avtobusa se v tabeli avtobusov premaknejo za en indeks nazaj
                        for (int i=indeksAvtobusa1; i<14; i++){
                            bus[i]=bus[i+1];
                        }
                        bus[14]=null;
                        // Izbris tekstovne datoteke s podatki izbranega avtobusa in zmanjšanje števila avtobusov.
                        Path filePath = Paths.get("Bus" + indeksAvtobusa1 + ".txt");
                        Files.delete(filePath);
                        for (int i = indeksAvtobusa1+1; i <= bus.length; i++){
                            File file = new File("Bus" + i + ".txt");
                            if(file.exists()) {
                                File fileNew = new File("Bus" + (i-1) + ".txt");
                                file.renameTo(fileNew);
                            }
                        }
                        Avtobus.steviloAvtobusov--;
                        vstop = false;
                    }
                    else if (n==3){
                        // Izpis vseh podatkov avtobusa, najprej izberemo kateri avtobus želimo izpisati
                        System.out.println("Podatke katerega avtobusa želite izpisati (izberite število)?");
                        for (int i=0; i<Avtobus.steviloAvtobusov; i++){
                            System.out.println(i+1+". "+ bus[i].getImeAvtobusa());
                        }
                        int indeksAvtobusa3 = Integer.parseInt(vh.readLine());
                        indeksAvtobusa3 -= 1;
                        System.out.println();
                        bus[indeksAvtobusa3].izpisVsehPodatkov();
                        n=0;
                        vstop = false;
                    }
                    else if (n==4){
                        vstop = true;
                    }
                }
            }
        }
    }
    // Metoda, ki polje tipa String napolni s presledki
    // Vhodni podatek je polje tipa String
    // Metoda ne vrne ničesar
    public static void napolniArray(String[] polje){
        for(int i=0;i< polje.length;i++){
            polje[i]=" ";
        }
    }
    // Metoda, ki polje tipa int napolni z 0
    // Vhodni podatek je polje tipa int
    // Metoda ne vrne ničesar
    public static void napolniArray(int[] polje){
        for(int i=0;i< polje.length;i++){
            polje[i]=0;
        }
    }
    // Metoda za ustvarjanje objekta tipa Bus iz tekstovne datoteke
    // Metoda nima vhodnega podatka in ne vrne ničesar
    public static void ustvariAvtobusIzTekstovneDatoteke()throws IOException{
        BufferedReader br;
        boolean fileExistence = true;
        int i = 0;
        while (fileExistence){
            try {
                br = new BufferedReader(new FileReader("Bus" + i + ".txt"));

                Avtobus.steviloAvtobusov ++;
                bus[i].setImeAvtobusa(br.readLine());
                bus[i].setImeVoznika(br.readLine());
                bus[i].setDneviVoznje(br.readLine().split(" "));

                String[] postaja = new String[15];
                postaja = br.readLine().split(" ");
                for (int j = 0; j < postaja.length; j++)
                    postaja[j] = postaja[j].replace("#", " ");
                bus[i].setPostaja(postaja);

                String[] vozniRedUreString = new String[15];
                int[] vozniRedUreInteger = new int[15];
                vozniRedUreString = br.readLine().split(" ");
                for (int j = 0; j < vozniRedUreString.length; j++)
                    vozniRedUreInteger[j] = Integer.parseInt(vozniRedUreString[j]);
                bus[i].setVozniRedUre(vozniRedUreInteger);

                String[] vozniRedMinuteString = new String[15];
                int[] vozniRedMinuteInteger = new int[15];
                vozniRedMinuteString = br.readLine().split(" ");
                for (int j = 0; j < vozniRedMinuteString.length; j++)
                    vozniRedMinuteInteger[j] = Integer.parseInt(vozniRedMinuteString[j]);
                bus[i].setVozniRedMinute(vozniRedMinuteInteger);

                bus[i].setSteviloPolnihDniVTednu(Integer.parseInt(br.readLine()));
                bus[i].setSteviloPolnihPostaj(Integer.parseInt(br.readLine()));

                i++;
                br.close();
            }
            catch (FileNotFoundException e){
                fileExistence = false;
            }

        }
    }


}