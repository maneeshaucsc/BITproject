package com.fillingstationproject.controller;



import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class ReportProvider {

    public static List<ReportEntitySystemAccessAnalysis> getSystemAccessAnalysisReport(LocalDateTime startdate, LocalDateTime enddate) {


        try {
            AuthProvider.setConnection();
            Statement st = AuthProvider.connection.createStatement();
            String query = "SELECT d.name as name, count(*) as attempt FROM orderandsales.sessionlog as s, orderandsales.user as u, orderandsales.employee as e, orderandsales.designation as d where s.user_id=u.id and u.employee_id=e.id and e.designation_id=d.id and s.logintime between '" + startdate + "' and '" + enddate + "' group by d.id ;";
            ResultSet rs = st.executeQuery(query);

            List<ReportEntitySystemAccessAnalysis> list = new ArrayList<>();

            while (rs.next()) {
                ReportEntitySystemAccessAnalysis report = new ReportEntitySystemAccessAnalysis(rs.getString("name"), rs.getInt("attempt"));
                list.add(report);
            }
            return list;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

        public static List<ReportEntityItembySupplier> getSuppliersReport () {


            try {
                AuthProvider.setConnection();
                Statement st = AuthProvider.connection.createStatement();
                String query = "SELECT i.code, i.name,i.quantity,i.rop FROM orderandsales.item as i,orderandsales.itemtype as it,orderandsales.supply as su where su.itemtype_id=it.id and it.id =i.itemtype_id and su.supplier_id=2 group by i.id ;";
                ResultSet rs = st.executeQuery(query);

                List<ReportEntityItembySupplier> list = new ArrayList<>();

                while (rs.next()) {
                    ReportEntityItembySupplier report = new ReportEntityItembySupplier(
                            rs.getString("code"),
                            rs.getString("name"),
                            rs.getInt("quantity"),
                            rs.getInt("rop"));
                    list.add(report);
                }
                return list;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                return null;
            }
        }

    public static List<ReportReadingmeter> getreadingReport(LocalDate startdate, LocalDate enddate) {


            try {
                AuthProvider.setConnection();
                Statement st = AuthProvider.connection.createStatement();
                String query = "SELECT  m.code,r.amount,r.income,r.entereddate FROM orderandsales.reading as r,orderandsales.meter as m where m.id=r.meter_id and entereddate between '"+startdate+"' and '"+enddate+"' group by r.id;";
                ResultSet rs = st.executeQuery(query);

                List<ReportReadingmeter> list = new ArrayList<>();

                while (rs.next()) {
                    ReportReadingmeter report = new ReportReadingmeter(rs.getString("code"),rs.getBigDecimal("amount"),rs.getBigDecimal("income"),rs.getString("entereddate"));
                    list.add(report);
                }
                return list;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                return null;
            }
        }











}



