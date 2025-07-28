package com.retail.erp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
public class ReportsController {

   /* @Autowired private EntityManager entityManager;
    @Autowired private ProductRepository productRepo;

    @GetMapping("/sales/daily")
    public List<DailySalesReport> getDailySalesReport() {
        String sql = " SELECT DATE(s.date) AS date, SUM(s.total_amount) AS totalSales FROM sale s GROUP BY DATE(s.date) ORDER BY DATE(s.date) DESC";
        List<Object[]> results = entityManager.createNativeQuery(sql).getResultList();
        List<DailySalesReport> reports = new ArrayList<>();
        for (Object[] row : results) {
            reports.add(new DailySalesReport(row[0].toString(), Double.parseDouble(row[1].toString())));
        }
        return reports;
    }

    @GetMapping("/stock")
    public List<Product> getStockSummary() {
    	System.out.println("dsfkdfk");
        return productRepo.findAll();
    }

    @GetMapping("/sales/top")
    public List<TopSellingProduct> getTopSellingProducts() {
        String sql = "SELECT p.name, SUM(li.quantity) AS totalQty FROM line_item li JOIN product p ON li.product_id = p.id WHERE li.sale_id IS NOT NULL GROUP BY p.name ORDER BY totalQty DESC LIMIT 5";
        List<Object[]> results = entityManager.createNativeQuery(sql).getResultList();
        List<TopSellingProduct> reports = new ArrayList<>();
        for (Object[] row : results) {
            reports.add(new TopSellingProduct(row[0].toString(), Integer.parseInt(row[1].toString())));
        }
        return reports;
    }*/
}
