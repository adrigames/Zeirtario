/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import adapter.Adapter;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import mediateca.Articulo;
import mediateca.*;
import javax.swing.JList;
import javax.swing.JOptionPane;
import mediateca.Fachada;
import mediateca.Parametro;
import proxy.ProxyQuery;
import strategy.*;

/**
 *
 * @author Adrian
 */
public class BusquedaArticulos extends javax.swing.JFrame {

    /**
     * Creates new form BusquedaArticulos
     */
    private Contexto con = new Contexto();
    private Estrategia est = new estrategiaTitulo();
    private ArrayList<Articulo> resultado= new ArrayList<Articulo>();
    private ProxyQuery proxy = new ProxyQuery(); 
    
    //Enumeraciones
    //Tipo
    private final int NINGUNO = 0;
    private final int AUDIO = 1;
    private final int CINE = 2;
    private final int COMIC = 3;
    private final int LIBRO = 4;
    private int tipoActivo = NINGUNO;
    //Orden
    private final int TITULO = 0;
    private final int AUTOR = 1;
    private final int GENERO = 2;
    private int ordenActivo = TITULO;
    
    //Comparar
    private final int MAYORQUE = 0;
    private final int MENORQUE = 1;
    private final int IGUAL = 2;
    private int compararActivo = MAYORQUE;
    
    public BusquedaArticulos() {
        initComponents();
        inicializarPantalla();
        
    }
    
    private void inicializarPantalla(){
        cmbTipo.setSelectedIndex(NINGUNO);
        cmbOrden.setSelectedIndex(TITULO);
        cmbOrden.setSelectedIndex(MAYORQUE);
        mostrarCampos();
        mostrarTabla();
        cambiarEstrategia();
    }
    
    private void mostrarCampos(){
        //Siempre desactivamos todos.
        lblDuracion.setVisible(false);
        txtDuracion.setVisible(false);
        lblPaginas.setVisible(false);
        txtPaginas.setVisible(false);
        lblActores.setVisible(false);
        txtActores.setVisible(false);
        lblIlustrador.setVisible(false);
        txtIlustrador.setVisible(false);
        lblComparar.setVisible(false);
        cmbComparar.setVisible(false);
        switch(tipoActivo){
            case NINGUNO:
                break;
            case AUDIO:
                lblDuracion.setVisible(true);
                txtDuracion.setVisible(true);
                lblComparar.setVisible(true);
                cmbComparar.setVisible(true);
                break;
            case CINE:
                lblDuracion.setVisible(true);
                txtDuracion.setVisible(true);
                lblActores.setVisible(true);
                txtActores.setVisible(true);
                lblComparar.setVisible(true);
                cmbComparar.setVisible(true);
                break;
            case COMIC:
                lblPaginas.setVisible(true);
                txtPaginas.setVisible(true);
                lblIlustrador.setVisible(true);
                txtIlustrador.setVisible(true);
                lblComparar.setVisible(true);
                cmbComparar.setVisible(true);
                break;
            case LIBRO:
                lblPaginas.setVisible(true);
                txtPaginas.setVisible(true);
                lblComparar.setVisible(true);
                cmbComparar.setVisible(true);     
        }
    }
    
    private void mostrarTabla(){
        DefaultTableModel modelo = (DefaultTableModel) tablaResultados.getModel();
        modelo.setColumnCount(3);

        
        modelo.setRowCount(0);
        switch(tipoActivo){
            case NINGUNO:
                break;
            case AUDIO:
                modelo.addColumn("Duracion");
                break;
            case CINE:
                modelo.addColumn("Duracion");
                modelo.addColumn("Actores");
                break;
            case COMIC:
                modelo.addColumn("Paginas");
                modelo.addColumn("Ilustrador");
                break;
            case LIBRO:
                modelo.addColumn("Paginas");
        }
    }
    
    private void borrarFiltrosEspecificos(){
    txtDuracion.setText("");
    txtPaginas.setText("");
    txtIlustrador.setText("");
    txtActores.setText("");
    
    }
    
    private void cargarGrid(ArrayList<Articulo> a){
        DefaultTableModel modelo = (DefaultTableModel) tablaResultados.getModel();
        modelo.setRowCount(0);
        Object rowData[];
        switch(tipoActivo){
            case AUDIO:
                rowData = new Object[4];
                break;
            case CINE:
                rowData = new Object[5];
                break;
            case COMIC:
                rowData = new Object[5];
                break;
            case LIBRO:
                rowData = new Object[4];
                break;
            default:
                rowData = new Object[3];
                break;
        }
        for(int i = 0; i<a.size(); i++){
            System.out.println(a.get(i).getClass());
            switch(tipoActivo){
                case AUDIO:
                    rowData[0] = a.get(i).getTitulo();
                    rowData[1] = a.get(i).getAutor();
                    rowData[2] = a.get(i).getGenero();
                    rowData[3] = ((Disco)a.get(i)).getDuracion();
                    break;
                case CINE:
                    rowData[0] = a.get(i).getTitulo();
                    rowData[1] = a.get(i).getAutor();
                    rowData[2] = a.get(i).getGenero();
                    rowData[3] = ((Pelicula)a.get(i)).getDuracion();
                    rowData[4] = ((Pelicula)a.get(i)).getActores();
                   break;
                case COMIC:
                    rowData[0] = a.get(i).getTitulo();
                    rowData[1] = a.get(i).getAutor();
                    rowData[2] = a.get(i).getGenero();
                    rowData[3] = ((Comic)a.get(i)).getPaginas();
                    rowData[4] = ((Comic)a.get(i)).getIlustrador();
                    break;
                case LIBRO:
                    rowData[0] = a.get(i).getTitulo();
                    rowData[1] = a.get(i).getAutor();
                    rowData[2] = a.get(i).getGenero();
                    rowData[3] = ((Libro)a.get(i)).getPaginas();
                default:
                    rowData[0] = a.get(i).getTitulo();
                    rowData[1] = a.get(i).getAutor();
                    rowData[2] = a.get(i).getGenero();
                    break;
            }
            modelo.addRow(rowData);
        }
    }
    
    private void cambiarEstrategia(){
        switch(ordenActivo){
            case TITULO:
                est = new estrategiaTitulo();
                break;
            case AUTOR:
                est = new estrategiaAutor();
                break;
            case GENERO:
                est = new estrategiaGenero();
                break;
        }
    }
    
    private ArrayList<Parametro> dameParametros(){
        Parametro p;
        ArrayList<Parametro> lista = new ArrayList<Parametro>();
        String prueba = txtTitulo.getText();
        if(!txtTitulo.getText().isEmpty()){
            p = new Parametro();
            p.setNombre("TITULO");
            p.setValor(txtTitulo.getText());
            lista.add(p);
        }
        if(!txtAutor.getText().isEmpty()){
            p = new Parametro();
            p.setNombre("AUTOR");
            p.setValor(txtAutor.getText());
            lista.add(p);
        }
        if(!txtGenero.getText().isEmpty()){
            p = new Parametro();
            p.setNombre("GENERO");
            p.setValor(txtGenero.getText());
            lista.add(p);
        }
        if(!txtDuracion.getText().isEmpty()){
            p = new Parametro();
            p.setNombre("DURACION");
            p.setValor(txtDuracion.getText());
            lista.add(p);
        }
        if(!txtPaginas.getText().isEmpty()){
            p = new Parametro();
            p.setNombre("PAGINAS");
            p.setValor(txtPaginas.getText());
            lista.add(p);
        }
        if(!txtIlustrador.getText().isEmpty()){
            p = new Parametro();
            p.setNombre("ILUSTRADOR");
            p.setValor(txtIlustrador.getText());
            lista.add(p);
        }
        if(!txtActores.getText().isEmpty()){
            p = new Parametro();
            p.setNombre("Actores");
            p.setValor(txtActores.getText());
            lista.add(p);
        }
        switch(compararActivo){
            case MAYORQUE:
                p = new Parametro();
                p.setNombre("COMPARADOR");
                p.setValor(">");
                lista.add(p);
                break;
            case MENORQUE:
                p = new Parametro();
                p.setNombre("COMPARADOR");
                p.setValor("<");
                lista.add(p);
                break;
            case IGUAL:
                p = new Parametro();
                p.setNombre("COMPARADOR");
                p.setValor("=");
                lista.add(p);
                break;
        }
        switch(tipoActivo){
            case NINGUNO:
                break;
            case AUDIO:
                p = new Parametro();
                p.setNombre("TIPO");
                p.setValor("AUDIO");
                lista.add(p);
                break;
            case CINE:
                p = new Parametro();
                p.setNombre("TIPO");
                p.setValor("CINE");
                lista.add(p);
                break;
            case COMIC:
                p = new Parametro();
                p.setNombre("TIPO");
                p.setValor("COMIC");
                lista.add(p);
                break;
            case LIBRO:
                p = new Parametro();
                p.setNombre("TIPO");
                p.setValor("LIBRO");
                lista.add(p);
                break;    
        }
        
        return lista;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblFiltrosEspecificos = new javax.swing.JLabel();
        lblFiltrosGenerales = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JTextField();
        lblTitulo = new javax.swing.JLabel();
        lblAutor = new javax.swing.JLabel();
        txtAutor = new javax.swing.JTextField();
        lblGenero = new javax.swing.JLabel();
        txtGenero = new javax.swing.JTextField();
        lblDuracion = new javax.swing.JLabel();
        txtIlustrador = new javax.swing.JTextField();
        lblPaginas = new javax.swing.JLabel();
        txtDuracion = new javax.swing.JTextField();
        lblIlustrador = new javax.swing.JLabel();
        txtPaginas = new javax.swing.JTextField();
        lblActores = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaResultados = new javax.swing.JTable();
        txtActores = new javax.swing.JTextField();
        cmbOrden = new javax.swing.JComboBox<>();
        lblGenero1 = new javax.swing.JLabel();
        lblComparar = new javax.swing.JLabel();
        cmbComparar = new javax.swing.JComboBox<>();
        cmbTipo = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnVer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblFiltrosEspecificos.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblFiltrosEspecificos.setText("Filtros especificos");
        lblFiltrosEspecificos.setToolTipText("Filtros especificos segun el tipo de articulo.");

        lblFiltrosGenerales.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblFiltrosGenerales.setText("Filtros generales");
        lblFiltrosGenerales.setToolTipText("Filtros que comparten todos nuestros articulos");

        txtTitulo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTituloActionPerformed(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblTitulo.setText("Titulo:");
        lblTitulo.setToolTipText("Titulo de la obra a buscar");

        lblAutor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblAutor.setText("Autor:");
        lblAutor.setToolTipText("Autor de la obra a buscar");

        txtAutor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtAutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAutorActionPerformed(evt);
            }
        });

        lblGenero.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblGenero.setText("Genero:");
        lblGenero.setToolTipText("Genero de la obra a buscar");

        txtGenero.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtGenero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGeneroActionPerformed(evt);
            }
        });

        lblDuracion.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblDuracion.setText("Duración:");
        lblDuracion.setToolTipText("Duración, en minutos, de la obra a buscar.");

        txtIlustrador.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtIlustrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIlustradorActionPerformed(evt);
            }
        });

        lblPaginas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblPaginas.setText("Paginas");
        lblPaginas.setToolTipText("Número de paginas de la obra a buscar");

        txtDuracion.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtDuracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDuracionActionPerformed(evt);
            }
        });

        lblIlustrador.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblIlustrador.setText("Ilustrador:");
        lblIlustrador.setToolTipText("Ilustrador de la obra a buscar");

        txtPaginas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtPaginas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPaginasActionPerformed(evt);
            }
        });

        lblActores.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblActores.setText("Actores:");
        lblActores.setToolTipText("Actores de la obra a buscar");

        tablaResultados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Titulo", "Autor", "Genero"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tablaResultados);

        txtActores.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtActores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtActoresActionPerformed(evt);
            }
        });

        cmbOrden.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Titulo", "Autor", "Genero" }));
        cmbOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbOrdenActionPerformed(evt);
            }
        });

        lblGenero1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblGenero1.setText("Orden:");
        lblGenero1.setToolTipText("Atributo por el que se ordenara la busqueda.");

        lblComparar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblComparar.setText("Comparar:");
        lblComparar.setToolTipText("Para los campos númericos, que tipo de búsqeda se realizará.");

        cmbComparar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { ">", "<", "=" }));
        cmbComparar.setToolTipText("");
        cmbComparar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCompararActionPerformed(evt);
            }
        });

        cmbTipo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ninguno", "Audio", "Cine", "Comic", "Libro" }));
        cmbTipo.setToolTipText("Tipo específico de articulo que desea buscar, seleccione uno para que se muestren los filtros");
        cmbTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoActionPerformed(evt);
            }
        });

        btnBuscar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.setToolTipText("Hace una busqueda con los datos introducidos");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnVer.setText("Ver");
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblFiltrosEspecificos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblFiltrosGenerales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(lblGenero1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblDuracion)
                                        .addComponent(lblActores))
                                    .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtActores)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(txtDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(36, 36, 36)
                                            .addComponent(lblPaginas)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtPaginas, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(57, 57, 57)
                                        .addComponent(lblAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(92, 92, 92)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblComparar)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblGenero, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblIlustrador, javax.swing.GroupLayout.Alignment.TRAILING))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnBuscar)
                                .addGap(22, 22, 22)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnVer)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtGenero)
                            .addComponent(txtIlustrador, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                            .addComponent(cmbOrden, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbComparar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFiltrosGenerales)
                    .addComponent(cmbOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGenero1))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitulo)
                    .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAutor)
                    .addComponent(txtAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGenero)
                    .addComponent(txtGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFiltrosEspecificos)
                    .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDuracion)
                    .addComponent(txtDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIlustrador)
                    .addComponent(txtIlustrador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPaginas)
                    .addComponent(txtPaginas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblActores)
                    .addComponent(txtActores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblComparar)
                    .addComponent(cmbComparar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar)
                    .addComponent(jButton1)
                    .addComponent(btnVer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTituloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTituloActionPerformed

    private void txtAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAutorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAutorActionPerformed

    private void txtGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGeneroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGeneroActionPerformed

    private void txtIlustradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIlustradorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIlustradorActionPerformed

    private void txtDuracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDuracionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDuracionActionPerformed

    private void txtPaginasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPaginasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPaginasActionPerformed

    private void txtActoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtActoresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtActoresActionPerformed

    private void cmbOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbOrdenActionPerformed
        // TODO add your handling code here:
        ordenActivo = cmbOrden.getSelectedIndex();
        cambiarEstrategia();
    }//GEN-LAST:event_cmbOrdenActionPerformed

    private void cmbCompararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCompararActionPerformed
        // TODO add your handling code here:
        compararActivo = cmbComparar.getSelectedIndex();
        
    }//GEN-LAST:event_cmbCompararActionPerformed

    private void cmbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoActionPerformed
        // TODO add your handling code here:
        tipoActivo = cmbTipo.getSelectedIndex();
        mostrarCampos();
        mostrarTabla();
        borrarFiltrosEspecificos();
    }//GEN-LAST:event_cmbTipoActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        ArrayList<Parametro> oParametro = dameParametros();
        Adapter ad = new Adapter();
        resultado = ad.seleccionarArticulo(oParametro);
        //resultado = proxy.seleccionarArticulo(oParametro);
        con.setEst(est);
        con.setLista(resultado);
        resultado = con.ejecutaEstrategia();
        cargarGrid(resultado);
        
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(Fachada.getAdminUser())
        {
            new InterfazAdmin().setVisible(true);
        }else{
            new InterfazUsuario().setVisible(true);
        }
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerActionPerformed
        // TODO add your handling code here:
        int i = tablaResultados.getSelectedRow();
        if(i == -1){
        Component frame = null;
        JOptionPane.showMessageDialog(frame, "Por favor, seleccione un articulo","Advertencia",JOptionPane.WARNING_MESSAGE);
        }else{
            
         new VerArticulo(resultado.get(i).getId()).setVisible(true);
             this.dispose();
        }
    }//GEN-LAST:event_btnVerActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BusquedaArticulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BusquedaArticulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BusquedaArticulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BusquedaArticulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BusquedaArticulos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnVer;
    private javax.swing.JComboBox<String> cmbComparar;
    private javax.swing.JComboBox<String> cmbOrden;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblActores;
    private javax.swing.JLabel lblAutor;
    private javax.swing.JLabel lblComparar;
    private javax.swing.JLabel lblDuracion;
    private javax.swing.JLabel lblFiltrosEspecificos;
    private javax.swing.JLabel lblFiltrosGenerales;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JLabel lblGenero1;
    private javax.swing.JLabel lblIlustrador;
    private javax.swing.JLabel lblPaginas;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tablaResultados;
    private javax.swing.JTextField txtActores;
    private javax.swing.JTextField txtAutor;
    private javax.swing.JTextField txtDuracion;
    private javax.swing.JTextField txtGenero;
    private javax.swing.JTextField txtIlustrador;
    private javax.swing.JTextField txtPaginas;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
