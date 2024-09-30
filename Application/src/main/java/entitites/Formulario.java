package entitites;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class Formulario extends javax.swing.JPanel {

    private String nome;
    private String dataDeNascimento;
    private String cpf;
    private Tabela tabela;

    public Formulario() {
        initComponents();
    }

    public void setTabela(Tabela tabela) {
        this.tabela = tabela;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nomeLabel = new javax.swing.JLabel();
        nomeField = new javax.swing.JTextField();
        dataLabel = new javax.swing.JLabel();
        dataField = new javax.swing.JTextField();
        CPFLabel = new javax.swing.JLabel();
        CPFField = new javax.swing.JTextField();
        adicionarButton = new javax.swing.JButton();
        removerButton = new javax.swing.JButton();
        editarButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        nomeLabel.setText("Nome");

        dataLabel.setText("Data de nascimento");

        CPFLabel.setText("CPF");

        adicionarButton.setText("Adicionar");
        adicionarButton.setPreferredSize(new java.awt.Dimension(100, 25));
        adicionarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarButtonActionPerformed(evt);
            }
        });

        removerButton.setText("Remover");
        removerButton.setPreferredSize(new java.awt.Dimension(100, 25));
        removerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerButtonActionPerformed(evt);
            }
        });

        editarButton.setText("Editar");
        editarButton.setPreferredSize(new java.awt.Dimension(100, 25));
        editarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(CPFField)
            .addComponent(dataField)
            .addComponent(nomeField)
            .addGroup(layout.createSequentialGroup()
                .addComponent(adicionarButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(removerButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nomeLabel)
                    .addComponent(dataLabel)
                    .addComponent(CPFLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(editarButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(nomeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nomeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(dataLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dataField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(CPFLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CPFField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adicionarButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removerButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(editarButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    public Object[] getLabel() throws CamposInvalidosException {
        nome = nomeField.getText();
        dataDeNascimento = dataField.getText();
        cpf = CPFField.getText();

        if (nome.equals("") || dataDeNascimento.equals("") || cpf.equals("")) {
            throw new CamposInvalidosException("Preencha todos os campos!");
        }

        if (!isValidNome(nome)) {
            throw new CamposInvalidosException("Nome inválido! O nome deve conter apenas letras e espaços.");
        }

        if (!isValidDate(dataDeNascimento)) {
            throw new CamposInvalidosException("Data de nascimento inválida! Formato esperado: DD/MM/AAAA.");
        }
        if (!isValidCPF(cpf)) {
            throw new CamposInvalidosException("CPF inválido! Formato esperado: DDD.DDD.DDD-DD.");
        }

        int idade = calculateAge(dataDeNascimento);

        return new Object[]{nome, dataDeNascimento, idade, cpf};
    }

    private int calculateAge(String birthDateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date birthDate = sdf.parse(birthDateStr);
            LocalDate today = LocalDate.now();
            LocalDate birthLocalDate = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(birthDate));
            return Period.between(birthLocalDate, today).getYears();
        } catch (ParseException e) {
            return 0;
        }
    }

    private boolean isValidDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            Date date = sdf.parse(dateStr);
            LocalDate today = LocalDate.now();
            LocalDate birthDate = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(date));
            return !birthDate.isAfter(today);
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean isValidCPF(String cpf) {
        String cpfPattern = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}";
        if (!cpf.matches(cpfPattern)) {
            return false;
        }

        String cleanCpf = cpf.replace(".", "").replace("-", "");

        if (cleanCpf.equals("00000000000") || cleanCpf.equals("11111111111")
                || cleanCpf.equals("22222222222") || cleanCpf.equals("33333333333")
                || cleanCpf.equals("44444444444") || cleanCpf.equals("55555555555")
                || cleanCpf.equals("66666666666") || cleanCpf.equals("77777777777")
                || cleanCpf.equals("88888888888") || cleanCpf.equals("99999999999")) {
            return false;
        }

        int[] CpfArray = CPFtoArray(cleanCpf);

        return verificaCodigo(1, CpfArray) != false;
    }

    private boolean isValidNome(String nome) {
        return nome.matches("^[A-Za-zÀ-ÖØ-öø-ÿ ]+$"); // Permite apenas letras e espaços (incluindo letras acentuadas)
    }

    private int[] CPFtoArray(String CPF) {
        int[] CpfArray = new int[11];
        for (int i = 0; i <= 10; i++) {
            CpfArray[i] = Integer.parseInt(String.valueOf(CPF.charAt(i)));
        }
        return CpfArray;
    }

    private boolean verificaCodigo(int posicaoCodigo, int[] Cpf) {
        int j = 0;
        if (posicaoCodigo == 1) {
            j = 10;
        } else {
            j = 11;
        }

        int indexParameter = 7 + posicaoCodigo;

        int resultado = 0;
        for (int i = 0; i <= indexParameter; i++) {
            resultado += j * Cpf[i];
            j--;
        }

        int restoDiv = resultado % 11;
        if (restoDiv < 2) {
            if (Cpf[indexParameter + 1] == 0) {
                if (posicaoCodigo == 1) {
                    return verificaCodigo(2, Cpf);
                } else {
                    return true;
                }
            } else {
                return false;
            }
        } else {
            int dif = 11 - restoDiv;
            if (Cpf[indexParameter + 1] == dif) {
                if (posicaoCodigo == 1) {
                    return verificaCodigo(2, Cpf);
                } else {
                    return true;
                }
            } else {
                return false;
            }
        }
    }

    private void editarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarButtonActionPerformed
        try {
            Object[] row = getLabel();
            tabela.editSelectedRow(row);
        } catch (CamposInvalidosException e) {
            e.getMessage();
        }
    }//GEN-LAST:event_editarButtonActionPerformed

    private void adicionarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarButtonActionPerformed
        try {
            Object[] row = getLabel();
            tabela.addRow(row);
        } catch (CamposInvalidosException e) {
            e.getMessage();
        }
    }//GEN-LAST:event_adicionarButtonActionPerformed

    private void removerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerButtonActionPerformed
        try {
            tabela.removeSelectedRow();
        } catch (CamposInvalidosException e) {
            e.getMessage();
        }
    }//GEN-LAST:event_removerButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CPFField;
    private javax.swing.JLabel CPFLabel;
    private javax.swing.JButton adicionarButton;
    private javax.swing.JTextField dataField;
    private javax.swing.JLabel dataLabel;
    private javax.swing.JButton editarButton;
    private javax.swing.JTextField nomeField;
    private javax.swing.JLabel nomeLabel;
    private javax.swing.JButton removerButton;
    // End of variables declaration//GEN-END:variables
}
