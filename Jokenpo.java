import java.util.Random;
import javax.swing.JOptionPane;

public class Jokenpo {
    public static final int PEDRA = 1;
    public static final int PAPEL = 2;
    public static final int TESOURA = 3;
    public static final int DERROTA = 0;
    public static final int VITORIA = 1;
    public static final int EMPATE = 2;

    private static final Random random = new Random();

    public static int jogadausuario = 0;
    private int jogadainimigo;

    public int getJogadainimigo() {
        return jogadainimigo;
    }

    public void setJogadainimigo(int jogadainimigo) {
        this.jogadainimigo = jogadainimigo;
    }

    public static int status;
    public static int pontosbot = 0, pontosjogador = 0;

    public static void titulo() {
        JOptionPane.showMessageDialog(null, "Jokenpô - Versão 2.1", "Bem-vindo", JOptionPane.INFORMATION_MESSAGE);
    }

    // Metodo onde o Menu principal se desenvolve
    public static void menu() {
        boolean continuar = true;

        while (continuar) {
            String input = JOptionPane.showInputDialog(null,
                    "[1] Pedra\n[2] Papel\n[3] Tesoura\n[0] Sair\nJogador1 " + pontosjogador + " VS Jogador2 " + pontosbot,
                    "Sua Jogada", JOptionPane.QUESTION_MESSAGE);

            if (input == null) {
                // Usuário cancelou a entrada
                break;
            }

            input = input.trim();

            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Erro: Entrada vazia, digite um número inteiro.");
                continue;
            }

            try {
                jogadausuario = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Erro: Entrada inválida, digite um número inteiro.");
                continue;
            }

            if (jogadausuario == 0) {
                break;
            }

            if (jogadausuario < PEDRA || jogadausuario > TESOURA) {
                JOptionPane.showMessageDialog(null, "Erro: escolha uma opção entre 1 e 3.");
                continue;
            }

            int jogadaInimigo = gerarJogadaInimigo();
            Jokenpo.getInstance().setJogadainimigo(jogadaInimigo);

            duelar();

            continuar = JOptionPane.showConfirmDialog(null, "Deseja continuar?", "Jogar novamente",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
        }

        JOptionPane.showMessageDialog(null,
                "Pontuação final\nJogador1: " + pontosjogador + "\nJogador2: " + pontosbot,
                "Resultado", JOptionPane.INFORMATION_MESSAGE);
    }

    private static int gerarJogadaInimigo() {
        return random.nextInt(3) + 1; // Retorna um valor entre 1 e 3
    }

    public static int duelar() {
        int jogadaInimigo = Jokenpo.getInstance().getJogadainimigo();

        if (jogadausuario == jogadaInimigo) {
            JOptionPane.showMessageDialog(null,
                    "Jogador1 jogou " + descricaoJogada(jogadausuario) + "\n" +
                            "Jogador2 jogou " + descricaoJogada(jogadaInimigo) + "\nEMPATE");
            status = EMPATE;
            return EMPATE;
        }

        if (jogadausuario == PEDRA && jogadaInimigo == PAPEL ||
                jogadausuario == PAPEL && jogadaInimigo == TESOURA ||
                jogadausuario == TESOURA && jogadaInimigo == PEDRA) {
            JOptionPane.showMessageDialog(null,
                    "Jogador1 jogou " + descricaoJogada(jogadausuario) + "\n" +
                            "Jogador2 jogou " + descricaoJogada(jogadaInimigo) + "\nJogador 2 Venceu! :(");
            status = DERROTA;
            pontosbot++;
            return DERROTA;
        }

        JOptionPane.showMessageDialog(null,
                "Jogador1 jogou " + descricaoJogada(jogadausuario) + "\n" +
                        "Jogador2 jogou " + descricaoJogada(jogadaInimigo) + "\nJogador 1 Venceu! :)");
        status = VITORIA;
        pontosjogador++;
        return VITORIA;
    }

    private static String descricaoJogada(int jogada) {
        return switch (jogada) {
            case PEDRA -> "Pedra";
            case PAPEL -> "Papel";
            case TESOURA -> "Tesoura";
            default -> "Desconhecido";
        };
    }

    private static Jokenpo instancia;

    private Jokenpo() {

    }

    public static synchronized Jokenpo getInstance() {
        if (instancia == null) {
            instancia = new Jokenpo();
        }
        return instancia;
    }
}
