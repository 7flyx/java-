import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-24
 * Time: 21:59
 * Description: 从文件中读取一张pgm格式的图片，然后将它模糊化
 */
public class BlurPGM {

    private static ImageData im = new ImageData();
    //    FILE *fIn;
    //    FILE *out =NULL;
    private static String filename = "untitled\\src\\im_test.pgm";
    private static String filename_out = "untitled\\im-blur.pgm";
    // mid_im is used to store the image after each blur and use it as the next blur.
    // Mid_im用于在每次模糊之后存储图像，并将其用作下一次模糊。
    private static int[][] mid_im;

    public static void main(String[] args) {
        read_pgm_file();
        System.out.println("Start blurring.");
        long start_blurring = System.currentTimeMillis();
        blur_image(20);
        long finish_blurring = System.currentTimeMillis();
        write_pgm_file();
        System.out.print("blurring Time " + (finish_blurring - start_blurring) * 1.0 / 1000 + "sec\n");
        System.out.println("Finish blurring.");
    }

    //  According to the format of the pgm image,
    //  we built a struct to store the corresponding information respectively.
    private static class ImageData {
        public byte[] version = new byte[3];
        private List<Character> imageLength; // 存储图片的长宽
        private List<Character> max_gv_list;
        public int row;
        public int col;
        public int max_gv;
        public int[][] matrix; // 存储图片的二进制数据

        public void init() {
            this.matrix = new int[this.row][this.col];
        }
    }

    // 从文件中读取图片
    public static void read_pgm_file() {
        FileInputStream fin = null;
        LineNumberInputStream fr = null;
        try {
            fin = new FileInputStream(new File(filename));
            fr = new LineNumberInputStream(fin); // 带有行号的读取方式
            try {
                // 读取version字段
                fr.read(im.version, 0, 2); // 0 1 两个位置的字符
                int curLine = fr.getLineNumber();
                while (fr.getLineNumber() == curLine) {
                    fr.read(); //跳过当前行的后续数据，将光标移动到下一行起始位置
                }

                // 读取图片的长宽
                im.imageLength = new ArrayList<>();
                curLine++; //移动到下一行
                while (fr.getLineNumber() == curLine) {
                    im.imageLength.add((char) fr.read());
                }
                // 将list中的数据，存储到ImageData对象中
                boolean flag = false; // 表示是否遇到了空格，标志着从col到row
                for (Character ch : im.imageLength) {
                    if (ch == ' ' || ch == '\n') {
                        flag = true;
                        continue;
                    }
                    if (flag) { // 填写 col
                        im.col = im.col * 10 + (ch - '0');
                    } else { // 填写row
                        im.row = im.row * 10 + (ch - '0');
                    }
                }
                im.max_gv_list = new ArrayList<>();
                curLine++;
                while (fr.getLineNumber() == curLine) {
                    im.max_gv_list.add((char) fr.read());
                }
                while (fr.getLineNumber() == curLine) { // 读取当前行的后续数据
                    fr.read();
                }
                // 填写max_gv
                for (Character ch : im.max_gv_list) {
                    if (ch != '\n') {
                        im.max_gv = im.max_gv * 10 + (ch - '0');
                    }
                }
                // 初始化ImageData中二维数组的大小
                im.init();

                // 读取图片的正文数据，放入二维数组中
                List<Character> list = new ArrayList<>();
                for (int i = 0; i < im.row; i++) {
                    curLine++; // 进入下一行
                    while (fr.getLineNumber() == curLine) {
                        list.add((char) fr.read());
                    }
                    int num = 0; // 参数值
                    int j = 0; // 列
                    for (Character ch : list) {
                        if(ch == '\n') {
                            break;
                        }
                        if (ch == ' ') {
                            im.matrix[i][j] = num;
                            j++;
                            num = 0;
                        } else {
                            num = num * 10 + (ch - '0');
                        }
                    }
                    list.clear(); // 清空表中数据
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) { // 打开失败的情况
            System.out.println("Open file failed.");
            e.printStackTrace();
        } finally {
            try {
                fin.close(); // 回收资源
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mid_im = im.matrix;
    }

    // 将图片写入文件中
    public static void write_pgm_file() {
        File file = new File(filename_out);
        FileOutputStream fou = null;
        try {
            fou = new FileOutputStream(file);
            try {
                im.version[2] = '\n';
                for (int i = 0; i < 3; i++) { // 写入版本号
                    fou.write(im.version[i]);
                }
                for (Character ch : im.imageLength) { // 写入长宽
                    fou.write(ch);
                }
                for (Character ch : im.max_gv_list) { // 写入 max_gv
                    fou.write(ch);
                }

                // 写入正文数据
                for (int i = 0; i < im.row; i++) {
                    int col = im.matrix[i].length;
                    for (int j = 0; j < col; j++) {
                        String s = im.matrix[i][j] + "";
                        int len = s.length();
                        for (int k = 0; k < len; k++) {
                            fou.write(s.charAt(k));
                        }
                        fou.write(' '); // 两个数据之间补充空格
                        fou.write(' '); // 两个数据之间补充空格
                    }
                    fou.write('\n'); // 写入换行符
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                fou.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void blur_image(int times) {
        for (int m = 0; m < times; m++) {
            // 我们首先处理边元素。

            /*对于图像矩阵的四个角上的元素，我们只需要计算周围三个元素的平均值。*/
            mid_im[0][0] = (im.matrix[0][1] + im.matrix[1][1] + im.matrix[1][0]) / 3;
            mid_im[im.row - 1][0] = (im.matrix[im.row - 1][1] + im.matrix[im.row - 2][0] + im.matrix[im.row - 2][1]) / 3;
            mid_im[0][im.col - 1] = (im.matrix[1][im.col - 1] + im.matrix[1][im.col - 2] + im.matrix[0][im.col - 2]) / 3;
            mid_im[im.row - 1][im.col - 1] = (im.matrix[im.row - 1][im.col - 2] + im.matrix[im.row - 2][im.col - 1] + im.matrix[im.row - 2][im.col - 2]) / 3;

            /*对于图像矩阵的四个边的元素(除了四个角)，我们只需要计算它周围五个元素的平均值。*/
            for (int i = 1; i < im.col - 1; i++) {
                mid_im[0][i] = (im.matrix[0][i - 1] + im.matrix[0][i + 1] + im.matrix[1][i - 1] + im.matrix[1][i] + im.matrix[1][i + 1]) / 5;
                mid_im[im.row - 1][i] = (im.matrix[im.row - 1][i - 1] + im.matrix[im.row - 2][i + 1] + im.matrix[im.row - 2][i - 1] + im.matrix[im.row - 2][i] + im.matrix[im.row - 1][i + 1]) / 5;
            }
            for (int i = 1; i < im.row - 1; i++) {
                mid_im[i][0] = (im.matrix[i - 1][0] + im.matrix[i - 1][1] + im.matrix[i][1] + im.matrix[i + 1][1] + im.matrix[i + 1][0]) / 5;
                mid_im[i][im.col - 1] = (im.matrix[i - 1][im.col - 1] + im.matrix[i + 1][im.col - 1] + im.matrix[i - 1][im.col - 2] + im.matrix[i][im.col - 2] + im.matrix[i + 1][im.col - 2]) / 5;
            }

            // 接下来，我们处理图像的中间部分

            /*对于图像中间部分的元素，我们计算周围8个元素的平均值并替换它们.*/
            for (int i = 1; i < (im.row - 1); i++) {
                for (int j = 1; j < (im.col - 1); j++) {
                    mid_im[i][j] =
                            (im.matrix[i - 1][j - 1] + im.matrix[i - 1][j] + im.matrix[i - 1][j + 1] + im.matrix[i][j - 1] +
                                    im.matrix[i][j + 1] + im.matrix[i + 1][j - 1] + im.matrix[i + 1][j] + im.matrix[i + 1][j + 1]) / 8;
                }
            }
            // 在模糊一次后，存储当前图像为下一个模糊
            im.matrix = mid_im;
        }
    }


}
