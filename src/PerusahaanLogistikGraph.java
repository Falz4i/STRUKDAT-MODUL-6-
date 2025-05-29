import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner; // Meskipun tidak digunakan di contoh ini, sering berguna
import java.util.Stack;  // Alternatif untuk DFS iteratif, tapi kita pakai rekursif

public class PerusahaanLogistikGraph {
    private int jumlahGudang; // Jumlah total gudang (nodes)
    private LinkedList<Integer>[] daftarAdjacency; // Daftar adjacency untuk representasi graph
    private String[] namaGudang; // Array untuk menyimpan nama gudang (A, B, C, ...)

    /**
     * Konstruktor untuk inisialisasi graph.
     * @param jumlahGudang Jumlah gudang dalam graph.
     */
    public PerusahaanLogistikGraph(int jumlahGudang) {
        this.jumlahGudang = jumlahGudang;
        daftarAdjacency = new LinkedList[jumlahGudang];
        namaGudang = new String[jumlahGudang];
        for (int i = 0; i < jumlahGudang; i++) {
            daftarAdjacency[i] = new LinkedList<>();
            namaGudang[i] = String.valueOf((char) ('A' + i)); // Menamai gudang A, B, C, dst.
        }
    }

    /**
     * Menambahkan jalur (edge) dari gudang sumber ke gudang tujuan.
     * Ini adalah graph berarah (directed graph).
     * @param sumber Indeks gudang sumber.
     * @param tujuan Indeks gudang tujuan.
     */
    public void tambahJalur(int sumber, int tujuan) {
        // Menambahkan edge dari sumber ke tujuan
        daftarAdjacency[sumber].add(tujuan);
    }

    /**
     * Menampilkan adjacency matrix dari graph.
     * Angka 1 menunjukkan ada jalur, 0 menunjukkan tidak ada jalur langsung.
     */
    public void tampilkanAdjacencyMatrix() {
        System.out.println("Adjacency Matrix:");
        int[][] matrix = new int[jumlahGudang][jumlahGudang];

        // Mengisi matrix berdasarkan daftar adjacency
        for (int i = 0; i < jumlahGudang; i++) {
            for (Integer tetangga : daftarAdjacency[i]) {
                matrix[i][tetangga] = 1;
            }
        }

        // Mencetak header kolom (nama gudang)
        System.out.print("   "); // Spasi untuk baris header
        for (int i = 0; i < jumlahGudang; i++) {
            System.out.print(namaGudang[i] + " ");
        }
        System.out.println();

        // Mencetak matrix dengan header baris (nama gudang)
        for (int i = 0; i < jumlahGudang; i++) {
            System.out.print(namaGudang[i] + "  ");
            for (int j = 0; j < jumlahGudang; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(); // Baris baru untuk kerapian
    }

    /**
     * Melakukan Breadth First Search (BFS) dari gudang awal.
     * BFS menjelajahi graph level per level.
     * @param gudangAwal Indeks gudang tempat BFS dimulai.
     */
    public void bfs(int gudangAwal) {
        System.out.print("Jalur BFS dari Gudang " + namaGudang[gudangAwal] + ": ");
        boolean[] dikunjungi = new boolean[jumlahGudang]; // Menandai gudang yang sudah dikunjungi
        Queue<Integer> antrian = new LinkedList<>(); // Antrian untuk BFS

        dikunjungi[gudangAwal] = true;
        antrian.add(gudangAwal);

        while (!antrian.isEmpty()) {
            int gudangSaatIni = antrian.poll();
            System.out.print(namaGudang[gudangSaatIni] + " ");

            // Iterasi melalui semua tetangga dari gudang saat ini
            for (int tetangga : daftarAdjacency[gudangSaatIni]) {
                if (!dikunjungi[tetangga]) {
                    dikunjungi[tetangga] = true;
                    antrian.add(tetangga);
                }
            }
        }
        System.out.println("\n"); // Baris baru setelah traversal selesai
    }

    /**
     * Fungsi utilitas rekursif untuk Depth First Search (DFS).
     * @param gudang Indeks gudang saat ini yang sedang dikunjungi.
     * @param dikunjungi Array boolean untuk melacak gudang yang sudah dikunjungi.
     * @param jalur String builder untuk membangun urutan jalur DFS.
     */
    private void dfsUtil(int gudang, boolean[] dikunjungi, StringBuilder jalur) {
        dikunjungi[gudang] = true;
        jalur.append(namaGudang[gudang]).append(" ");

        // Rekursif mengunjungi semua tetangga yang belum dikunjungi
        for (int tetangga : daftarAdjacency[gudang]) {
            if (!dikunjungi[tetangga]) {
                dfsUtil(tetangga, dikunjungi, jalur);
            }
        }
    }

    /**
     * Melakukan Depth First Search (DFS) dari gudang awal.
     * DFS menjelajahi sejauh mungkin di satu cabang sebelum mundur (backtracking).
     * @param gudangAwal Indeks gudang tempat DFS dimulai.
     */
    public void dfs(int gudangAwal) {
        System.out.print("Jalur DFS dari Gudang " + namaGudang[gudangAwal] + ": ");
        boolean[] dikunjungi = new boolean[jumlahGudang]; // Menandai gudang yang sudah dikunjungi
        StringBuilder jalur = new StringBuilder(); // Untuk menyimpan urutan jalur DFS

        dfsUtil(gudangAwal, dikunjungi, jalur); // Memanggil fungsi utilitas rekursif
        System.out.println(jalur.toString().trim());
        System.out.println(); // Baris baru untuk kerapian
    }

    public static void main(String[] args) {
        int jumlahTotalGudang = 5; // Sesuai soal: 5 gudang
        PerusahaanLogistikGraph graph = new PerusahaanLogistikGraph(jumlahTotalGudang);

        // Gudang direpresentasikan dengan indeks: A=0, B=1, C=2, D=3, E=4

        // Menambahkan 7 jalur pengiriman sesuai instruksi
        // 1. A -> B
        graph.tambahJalur(0, 1);
        // 2. A -> C
        graph.tambahJalur(0, 2);
        // 3. B -> D
        graph.tambahJalur(1, 3);
        // 4. C -> D
        graph.tambahJalur(2, 3);
        // 5. C -> E
        graph.tambahJalur(2, 4);
        // 6. D -> E
        graph.tambahJalur(3, 4);
        // 7. E -> A (membuat siklus, yang umum dalam graph)
        graph.tambahJalur(4, 0);

        // Menampilkan Adjacency Matrix
        graph.tampilkanAdjacencyMatrix();

        // Melakukan BFS dari Gudang A (indeks 0)
        graph.bfs(0);

        // Melakukan DFS dari Gudang A (indeks 0)
        graph.dfs(0);
    }
}
