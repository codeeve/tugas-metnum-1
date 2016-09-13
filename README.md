# tugas-metnum-1
tugas kuliah metnum 1

ya isinya buat tugas 1 kuliah metnum menghitung nilai buat penerjun parasut dari start statis

##how to use
tinggal input nilai yang diketahui.

nilai yang nda diketahui isikan 0 aja nda masalah.

##output
==================================================
  Program Perhitungan Bisection & False Position  
==================================================
Program ini akan menghitung dengan persamaan 
jatuh parasut v=(gm/c)*(1-e^((c/m)*t))

Silakan masukkan nilai yang diketahui
m : 68.1
c : 0
g : 9.8
t : 10
v : 40
xl awal        : 12
xu awal        : 16
Ea target      : 0.1
limit Iterasi  : 100
dicari         : c
==================================================
                bisection method
==================================================
   iterasi        xl        xu        xr     Ea(%)
==================================================
         0        12        16        14       100
         1        14        16        15    6.6667
         2        15        16      15.5    3.2258
         3        15      15.5     15.25    1.6393
         4        15     15.25    15.125    0.8264
         5        15    15.125   15.0625    0.4149
         6        15   15.0625   15.0312    0.2079
         7        15   15.0312   15.0156    0.1041
         8        15   15.0156   15.0078    0.0521
==================================================
                false-position method
==================================================
   iterasi        xl        xu        xr     Ea(%)
==================================================
         0        12        16   14.9113       100
         1   14.9113        16   14.7739    0.9302
         2   14.7739        16   14.7805    0.0448
==================================================
