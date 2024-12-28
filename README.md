# Requisitos
- Java versión 21 en adelante
- Sistema operativo GNU/Linux / Windows
- Graphviz versión 9 en adelante 

## Advertencias
- En caso de colocar palabras no es necesario palabras, aun que pueda que sea necesario para algunos casos especificos.
- No utilizar # al final, el programa lo colocara de manera automatica.
- Para respresentar Epsilon, utilizar la E (Mayuscula).
- asegurate de usar correctamente los parentesis para agrupar.
- No hay problema con los espacios entre tokens.

## Ayudas
- * (Asterisco): Significa "cero o más" ocurrencias del elemento anterior.
- ? (Signo de interrogación):Indica "cero o una" ocurrencia del elemento anterior. Hace que el elemento sea opcional.
- + (Signo de más): Significa "una o más" ocurrencias del elemento anterior.
- . (Punto): Actúa como un "y" lógico, representa una union entre 2 o mas elementos
- | (Barra vertical o alternancia): Actúa como un "o" lógico. Permite que coincidan una de varias opciones posibles.

## Ejemplos de gramatica
- (a|b.c|c.d).(a|b.c|c.d)*.(h|E)
- (a|b|c)*
- (a|b|c).(d|b).d*
- (bar|foo)+.baz

<br>

### Árbol
![arbolEjemplo.svg](/Documentacion/arbolEjemplo.svg)

### Tabla de siguientes
![siguientesEjemplo.svg](/Documentacion/siguientesEjemplo.svg)

### Transiciones
![transicionesEjemplo.png](/Documentacion/transicionesEjemplo.png)

### Automata
![automataEjemplo.svg](/Documentacion/automataEjemplo.svg)


## Notas
Cualquier recomendación, sujerencia o cambio dejarlo en el apartado de issues
