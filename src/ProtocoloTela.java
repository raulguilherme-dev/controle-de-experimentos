import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class ProtocoloTela extends JFrame {

    private JLabel jLabelId, jLabelDataEmissaoProtocolo, jLabelDataInicioExperimento, jLabelDataFimExperimento,
    jLabelJustificativaUsoAnimais, jLabelResumoPortugues, jLabelResumoIngles, jLabelParecer;

    private JTextField jTextId, jTextDataEmissaoProtocolo, jTextDataInicioExperimento, jTextDataFimExperimento,
    jTextJustificativaUsoAnimais, jTextResumoPortugues, jTextResumoIngles;

    private JComboBox parecer;


    public ProtocoloTela() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setTitle("Controle de Experimento");
        this.setVisible(true);
        this.setLayout(new BorderLayout());

        JPanel jPanelGrid = new JPanel();
        jPanelGrid.setLayout(new GridLayout(10, 2));

        JPanel gridButtons = new JPanel();
        gridButtons.setLayout(new GridLayout(1, 5));

        JPanel segundaTela = new JPanel();
        segundaTela.setLayout(new GridLayout(8, 2));

        jLabelId = new JLabel("Id: ");
        jLabelDataEmissaoProtocolo = new JLabel("Data Emissão Protocolo:");
        jLabelDataInicioExperimento = new JLabel("Data Inicio Experimento:");
        jLabelDataFimExperimento = new JLabel("Data Fim Protocolo:");
        jLabelJustificativaUsoAnimais = new JLabel("Justificativa para uso de animais: ");
        jLabelResumoPortugues = new JLabel("Resumo em Português:");
        jLabelResumoIngles = new JLabel("Resumo em Inglês:");
        jLabelParecer = new JLabel("Parecer: ");


        jTextId = new JTextField(3);
        jTextDataEmissaoProtocolo = new JTextField(10);
        jTextDataInicioExperimento = new JTextField(10);
        jTextDataFimExperimento = new JTextField(10);
        jTextJustificativaUsoAnimais = new JTextField(10);
        jTextResumoPortugues = new JTextField(10);
        jTextResumoIngles = new JTextField(10);

        String ops[] = {"1. Uso Aprovado", "2. Uso Reprovado"};
        parecer = new JComboBox(ops);


        JButton emitir = new JButton("Emitir");
        JButton enviarParecer = new JButton("Enviar para Parecer");
        JButton emitirParecer = new JButton("Emitir Parecer");
        JButton deliberarProtocolo = new JButton("Deliberar Protocolo");
        JButton selecionar = new JButton("Selecionar");

        jPanelGrid.add(jLabelDataEmissaoProtocolo);
        jPanelGrid.add(jTextDataEmissaoProtocolo);
        jPanelGrid.add(jLabelDataInicioExperimento);
        jPanelGrid.add(jTextDataInicioExperimento);
        jPanelGrid.add(jLabelDataFimExperimento);
        jPanelGrid.add(jTextDataFimExperimento);
        jPanelGrid.add(jLabelJustificativaUsoAnimais);
        jPanelGrid.add(jTextJustificativaUsoAnimais);
        jPanelGrid.add(jLabelResumoPortugues);
        jPanelGrid.add(jTextResumoPortugues);
        jPanelGrid.add(jLabelResumoIngles);
        jPanelGrid.add(jTextResumoIngles);

        gridButtons.add(emitir);
        gridButtons.add(enviarParecer);
        gridButtons.add(emitirParecer);
        gridButtons.add(deliberarProtocolo);
        gridButtons.add(selecionar);

        segundaTela.add(jLabelId);
        segundaTela.add(jTextId);
        segundaTela.add(jLabelParecer);
        segundaTela.add(parecer);

        this.add(jPanelGrid, BorderLayout.WEST);
        this.add(gridButtons, BorderLayout.SOUTH);
        this.add(segundaTela, BorderLayout.EAST);


        emitir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                emitirMouseClicked(evt);
            }
        });

        enviarParecer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                enviarParecerMouseClicked(evt);
            }
        });

        emitirParecer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String op = String.valueOf(parecer.getSelectedItem());
                emitirParecerMouseClicked(evt);
            }
        });

        deliberarProtocolo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deliberarProtocoloMouseClicked(evt);
            }
        });

        selecionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    selecionarMouseClicked(evt);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void emitirMouseClicked(MouseEvent evt) {
        ProtocoloDAO proDao = new ProtocoloDAO();
        Protocolo protocolo = new Protocolo();
        protocolo.setDataEmissaoProtocolo(jTextDataEmissaoProtocolo.getText().strip());
        protocolo.setDataInicioExperimento(jTextDataInicioExperimento.getText().strip());
        protocolo.setDataFimExperimento(jTextDataFimExperimento.getText().strip());
        protocolo.setJustificativaUsoAnimais(jTextJustificativaUsoAnimais.getText().strip());
        protocolo.setResumoPortugues(jTextResumoPortugues.getText().strip());
        protocolo.setResumoIngles(jTextResumoIngles.getText().strip());

        try {
            proDao.emitir(protocolo);
        } catch (Exception ex) {
            Logger.getLogger(Protocolo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void enviarParecerMouseClicked(MouseEvent evt) {
        ProtocoloDAO proDao = new ProtocoloDAO();
        Protocolo protocolo = new Protocolo();
        protocolo.setId(Integer.parseInt(jTextId.getText().strip()));

        try {
            proDao.enviarParecer(protocolo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void emitirParecerMouseClicked(MouseEvent evt) {
        ProtocoloDAO proDao = new ProtocoloDAO();
        Protocolo protocolo = new Protocolo();
        protocolo.setId(Integer.parseInt(jTextId.getText().strip()));
        protocolo.setDescricaoParecer((String) parecer.getSelectedItem());

        try {
            proDao.emitirParecer(protocolo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deliberarProtocoloMouseClicked(MouseEvent evt) {
        ProtocoloDAO proDao = new ProtocoloDAO();
        Protocolo protocolo = new Protocolo();
        protocolo.setId(Integer.parseInt(jTextId.getText().strip()));

        try {
            proDao.deliberarProtocolo(protocolo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void selecionarMouseClicked(MouseEvent evt) throws Exception {

        SelecionarTela novaTela = new SelecionarTela();
    }

}
