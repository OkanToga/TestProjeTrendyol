@All

Feature: Bilgisayar arat ve sepete ekle
  Scenario: Bilgisayar arat ve en pahalı olanı sepete ekle
    Given Google sitesine gidilir
    When "Trendyol" aratilir
    And Trendyol websitesi acilir
    Then Trendyol reklam kapat
    Then "Bilgisayar" trendyolda aratilir
    When Liste acilir
    Then En yuksek fiyat secilir
    And Bilgisayar sayfasi acilir
    When Bildirim kapat
    Then Urunu sepete ekler
    When Sepeti acar
    Then Sepet kontrol


