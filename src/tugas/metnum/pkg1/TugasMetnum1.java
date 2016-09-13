/*
 * ini apps buat hitung
 */
package tugas.metnum.pkg1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Math.abs;
import java.text.DecimalFormat;

/**
 *
 * @author Ratten
 */
public class TugasMetnum1 {
    
    public static void main(String[] args) throws IOException {
        TugasMetnum1 MetNum = new TugasMetnum1();
        DecimalFormat decimalFormat = new DecimalFormat("0.####");
        //setup pertama kali
        System.out.println("==================================================");
        System.out.println("  Program Perhitungan Bisection & False Position  ");
        System.out.println("==================================================");
        System.out.println("Program ini akan menghitung dengan persamaan \n"
                          + "jatuh parasut v=(gm/c)*(1-e^((c/m)*t))");
        System.out.println();
        System.out.println("Silakan masukkan nilai yang diketahui");
        
        //setup input untuk nilai yang diketahui
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("m : "); double m = Double.parseDouble(br.readLine());
        System.out.print("c : "); double c = Double.parseDouble(br.readLine());
        System.out.print("g : "); double g = Double.parseDouble(br.readLine());
        System.out.print("t : "); double t = Double.parseDouble(br.readLine());
        System.out.print("v : "); double v = Double.parseDouble(br.readLine());
        
    /* =====================================================================
    /*                        BISECTION METHOD
    /* =====================================================================*/
        
        //hitung bisect
        
        System.out.print("xl awal        : "); double xl = Double.parseDouble(br.readLine());
        System.out.print("xu awal        : "); double xu = Double.parseDouble(br.readLine());
        System.out.print("Ea target      : "); double eaTarget = Double.parseDouble(br.readLine());
        System.out.print("limit Iterasi  : "); int imax = Integer.parseInt(br.readLine());
        System.out.print("dicari         : "); String dicari = br.readLine();
        //System.out.println("==================================================");
        System.out.println("==================================================");
        System.out.println("                bisection method");
        System.out.println("==================================================");
        System.out.format("%10s%10s%10s%10s%10s", "iterasi", "xl", "xu","xr", "Ea(%)");
        System.out.println();
        System.out.println("==================================================");

                
        //inisiasi nilai awal
        double xlAwal = xl;
        double xuAwal = xu;
        
        int i = 0;
        double ea = 100;
        double fXl = 0;
        double fXu = 0;
        double xr = 0;
        double xrSebelumnya = 0;
        
        //mulai iterasi
        //iterasi dilakukan sampai limit iterasi didapat atau dalam batas eror
        while ((ea > eaTarget) && i< imax){
            
            //cetak kedalam tabel
            
            //mengikuti powerpoint halaman  7
            //http://unhas.ac.id/amil/S2TE/pmn2016/02pmn2016.pdf
            
            System.out.format("%10d%10s%10s", i, decimalFormat.format(xl), decimalFormat.format(xu));
            
            //step 1
            //tentukan rumus yang dipake sesuai variable yang dicari
            switch(dicari){
                case "m":
                case "M": 
                        //hitung f(xl) & f(xu)
                        fXl = MetNum.hitungF(g,xl,c,t,v);
                        fXu = MetNum.hitungF(g,xu,c,t,v);
                        break;
                case "g":
                case "G": 
                        //hitung f(xl) & f(xu)
                        fXl = MetNum.hitungF(xl,m,c,t,v);
                        fXu = MetNum.hitungF(xu,m,c,t,v);
                        break;
                case "c":
                case "C": 
                        //hitung f(xl) & f(xu)
                        fXl = MetNum.hitungF(g,m,xl,t,v);
                        fXu = MetNum.hitungF(g,m,xu,t,v);
                        break;
                case "t":
                case "T": 
                        //hitung f(xl) & f(xu)
                        fXl = MetNum.hitungF(g,m,c,xl,v);
                        fXu = MetNum.hitungF(g,m,c,xu,v);
                        break;
                case "v":
                case "V": 
                        //hitung f(xl) & f(xu)
                        fXl = MetNum.hitungF(g,m,c,t,xl);
                        fXu = MetNum.hitungF(g,m,c,t,xu);
                        break;
                default: i = imax; break;
            }
            
            //break function kalau variable yang dicari salah input
            if(i==imax){ 
                System.out.println("nilai yang dicari tidak diketahui menghentikan iterasi");
                break; 
            }
            
            //System.out.print("fxl: "+fXl+" ");
            //System.out.print("fxu: "+fXu+" ");
            
            //Step 2
            //tentukan Xr
            xrSebelumnya = xr;
            xr = MetNum.hitungXrBisect(xl, xu);
            
            //System.out.print("xr: "+xr+" ");
            
            //step 3
            //subintervalcheck
            //kalau f(xl)*f(xu) < 0, akarnya ada di antara xl dan xr. jadi set xl = xr
            //kalau f(xl)*f(xu) > 0, akarnya ada di antara xr dan xu. jadi set xu = xr
            //kalau f(xl)*f(xu) = 0, perhitungan selesai xr adalah hasil yang dicari
            double tocheck = fXl * fXu;
            if(tocheck < 0){
                xl = xr;
            } else if (tocheck > 0){
                xu = xr;
            } else if (tocheck == 0){
                System.out.print("nilai ditemukan: "+xr);
                break; 
            }
            
            //hitung nilai Ea untuk penghentian loop
            ea = MetNum.hitungEa(xr, xrSebelumnya);
            
            System.out.format("%10s", decimalFormat.format(xr));
            System.out.format("%10s", decimalFormat.format(ea));
            System.out.println();
            
            //System.out.println();
            //tingkatkan nilai iterasi
            i++;    
        }
        
        
    /* =====================================================================
    /*                        FALSE POSITION METHOD
    /* =====================================================================*/
        //hitung bisect
        System.out.println("==================================================");
        System.out.println("                false-position method");
        System.out.println("==================================================");
        System.out.format("%10s%10s%10s%10s%10s", "iterasi", "xl", "xu","xr", "Ea(%)");
        System.out.println();
        System.out.println("==================================================");
        
        xl = xlAwal;
        xu = xuAwal;
        i = 0;
        ea = 100;
        fXl = 0;
        fXu = 0;
        xr = 0;
        xrSebelumnya = 0;
        
        //mulai iterasi
        //iterasi dilakukan sampai limit iterasi didapat atau dalam batas eror
        //mengikuti powerpoint halaman  16
            //http://unhas.ac.id/amil/S2TE/pmn2016/02pmn2016.pdf
        while ((ea > eaTarget) && i< imax){
            //cetak kedalam tabel
            //System.out.print("iterasi: "+i+" ");
            //System.out.print("xl: "+xl+" ");
            //System.out.print("xu: "+xu+" ");
            System.out.format("%10d%10s%10s", i, decimalFormat.format(xl), decimalFormat.format(xu));
            
            //step 1 hitung fxl fxu
            //tentukan rumus yang dipake sesuai variable yang dicari
            switch(dicari){
                case "m":
                case "M": 
                        //hitung f(xl) & f(xu)
                        fXl = MetNum.hitungF(g,xl,c,t,v);
                        fXu = MetNum.hitungF(g,xu,c,t,v);
                        break;
                case "g":
                case "G": 
                        //hitung f(xl) & f(xu)
                        fXl = MetNum.hitungF(xl,m,c,t,v);
                        fXu = MetNum.hitungF(xu,m,c,t,v);
                        break;
                case "c":
                case "C": 
                        //hitung f(xl) & f(xu)
                        fXl = MetNum.hitungF(g,m,xl,t,v);
                        fXu = MetNum.hitungF(g,m,xu,t,v);
                        break;
                case "t":
                case "T": 
                        //hitung f(xl) & f(xu)
                        fXl = MetNum.hitungF(g,m,c,xl,v);
                        fXu = MetNum.hitungF(g,m,c,xu,v);
                        break;
                case "v":
                case "V": 
                        //hitung f(xl) & f(xu)
                        fXl = MetNum.hitungF(g,m,c,t,xl);
                        fXu = MetNum.hitungF(g,m,c,t,xu);
                        break;
                default: i = imax; break;
            }
            
            //break function kalau variable yang dicari salah input
            if(i==imax){ 
                System.out.println("nilai yang dicari tidak diketahui menghentikan iterasi");
                break; 
            }
            
            //Step 2
            //tentukan Xr
            xrSebelumnya = xr;
            xr = MetNum.hitungXrFalsePosition(xl, xu, fXl, fXu);
            
            double fXr = 0;
            switch(dicari){
                case "m":
                case "M": 
                        fXr = MetNum.hitungF(g,xr,c,t,v);
                        break;
                case "g":
                case "G": 
                        fXr = MetNum.hitungF(xr,m,c,t,v);
                        break;
                case "c":
                case "C": 
                        fXr = MetNum.hitungF(g,m,xr,t,v);
                        break;
                case "t":
                case "T": 
                        fXr = MetNum.hitungF(g,m,c,xr,v);
                        break;
                case "v":
                case "V": 
                        fXr = MetNum.hitungF(g,m,c,t,xr);
                        break;
                default: i = imax; break;
            }
            
            //step 3
            //subintervalcheck
            //kalau f(xl)*f(xu) < 0, akarnya ada di antara xl dan xr. jadi set xu = xr
            //kalau f(xl)*f(xu) > 0, akarnya ada di antara xr dan xu. jadi set xl = xr
            //kalau f(xl)*f(xu) = 0, perhitungan selesai xr adalah hasil yang dicari
            double tocheck = fXl * fXr;
            if(tocheck < 0){
                xl = xr;
            } else if (tocheck > 0){
                xu = xr;
            } else if (tocheck == 0){
                System.out.print("nilai ditemukan: "+xr);
                break; 
            }
            
            if (i>0){
                //hitung nilai Ea untuk penghentian loop
                ea = MetNum.hitungEa(xr, xrSebelumnya);
            }
            
            
            System.out.format("%10s", decimalFormat.format(xr));
            System.out.format("%10s", decimalFormat.format(ea));
            System.out.println();
            //tingkatkan nilai iterasi
            i++;    
            
        }
        
        System.out.println("==================================================");
    }
    
    
    //hitung nilai fx
    //f=(g*m/c)*(1-e^((c/m)*t))-v
    private double hitungF(double g, double m, double c, double t, double v){
        double a = (g*m)/c;
        double b = -(c/m)*t;
        double x = Math.pow(2.71828,b);
        double fx = (a*(1- x))-v;
        return fx;
    }
    
    //hitung nilai Xr
    //nilai Xr berada di tengah nilai Xl dan Xu
    private double hitungXrBisect(double xl, double xu){
        return (xl+xu)/2;
    }
    
    //hitung nilai Xr false position
    //xr = xu - (fxu*(xl - xu))/(fxl-fxu)
    private double hitungXrFalsePosition(double xl, double xu, double fXl, double fXu){
        double a = (xl - xu);
        double b = (fXl-fXu);
        return (xu - ((fXu*a)/b));
    }
    
    //hitung approx error
    //Ea = ((present approx - previous approx) / present approx)*100
    double hitungEa(double nSekarang, double nSebelumnya){
        double Ea = abs(((nSekarang-nSebelumnya)/nSekarang)*100);
        return Ea;
    }
    
    
}
