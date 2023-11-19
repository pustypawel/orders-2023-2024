# Obsługa zamówień

1. Utwórz koszyk
2. Dodaj produkt (określ ilość)
3. Inkrementuj ilość
4. Dekrementuj ilość
5. Ustaw ilość
6. Usuń produkt

Warunki biznesowe:

1. Dodanie produktu, który jest już w koszyku skutkuje inkrementacją ilości

Struktury danych:

### Kontekst zamówienia

Zamówienie (Order):

- id
- data utworzenia
- data ostatniej modyfikacji
- lista pozycji
- wartość zamówienia

Pozycja zamówienia (OrderItem):

- produkt
- ilość
- wartość

### Kontekst produktów

Produkt:

- id
- nazwa
- cena
- data utworzenia
- data ostatniej modyfikacji


## Docelowo podział na moduły:

- api
- application (api)
- infrastructure (api, application)
- runner (api, application, infrastructure)
- cli
