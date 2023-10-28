package com.uade.ainews;

import com.uade.ainews.newsGeneration.dto.News;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TestUtils {

    public static News createNews() {
        List<String> keywords1 = new LinkedList<>(Arrays.asList("A", "B", "C", "D", "E"));
        LocalDateTime date = LocalDateTime.now();
        return News.builder().url("aURL").keywords(keywords1).section("aSection").title("aTitle").article("anArticle").releaseDate(date).build();
    }

    public static News createNewsWithLongContent() {
        List<String> keywords1 = new LinkedList<>(Arrays.asList("A", "B", "C", "D", "E"));
        LocalDateTime date = LocalDateTime.now();
        return News.builder()
                .url("https://www.perfil.com/noticias/bloomberg/bc-radicalismo-de-milei-no-es-lo-que-argentina-necesita.phtml")
                .keywords(keywords1)
                .section("aSection")
                .title("El radicalismo de Milei no es lo que la Argentina necesita")
                .article("El inesperado éxito de Javier Milei en las elecciones primarias nacionales de Argentina ha llevado al pensamiento radical, por no decir extravagante, al frente de la política del país. El creciente malestar por la economía le dio al furibundo insurgente del libre mercado su victoria preliminar, y ahora parece posicionado para tener un buen desempeño en las elecciones presidenciales de octubre. Ciertamente no le falta ambición. Promete transformar el país mediante el desmantelamiento de su Gobierno, nada menos que cerrando el banco central y dolarizando la economía. Un historial económico tan nefasto como el de Argentina invita a cierto radicalismo, pero tal vez no del tipo que propone Milei. Llamó al cambio climático “otra mentira del socialismo”. Quiere liberar el uso “legítimo y responsable” de las armas de fuego. No contento con recortar drásticamente el gasto público, quiere eliminar programas completos (incluida la atención de la salud pública) y cerrar muchos ministerios. Junto con el resto de esta agenda para desmantelar el sistema, cerrar el banco central —o, como Milei prefiere decir, quemarlo— parece casi modesto. Para una economía crónicamente mal administrada, resulta que hay un argumento respetable para la dolarización. Podría decirse que eso ya está sucediendo de manera espontánea, dado que los argentinos ahorran y realizan todas las transacciones en dólares que les son posibles. Ir más allá y abandonar formalmente el peso imposibilitaría que el Gobierno financie el gasto público mediante la emisión de dinero, como ha optado con frecuencia. La tasa de inflación actual de más del 100% (y en aumento) es el resultado predecible de un exceso de endeudamiento público combinado con un “dominio fiscal”, como se le llama, sobre el banco central. Para el Llamamiento Argentino Judío, Javier Milei es una expresión del \"ultraliberalismo limítrofe con el fascismo\" Bajo las condiciones adecuadas, los Gobiernos que se niegan a sí mismos esta opción pueden lograr un grado de moderación que de otro modo sería imposible. Y esto no es solo teoría: de vez en cuando —como es el caso de Panamá y El Salvador— la idea se ha empleado con buenos resultados. Las consecuencias de dolarizar El principal inconveniente es que al descartar una política monetaria irresponsable también se descarta la que sí es deseable: utilizar las tasas de interés y las fluctuaciones del tipo de cambio para estabilizar la demanda agregada y amortiguar los impactos económicos. El Gobierno también pierde el señoreaje que se acumula con la creación de efectivo. Se necesitarían impuestos más altos para compensar la diferencia. (Milei considera que el señoreaje es un robo, pero su plan no lo elimina; en su lugar, Estados Unidos recaudaría las ganancias). Desafortunadamente, aunque la dolarización a veces puede promover la disciplina, no ofrece ninguna garantía. El Gobierno aún podría pedir prestado (en dólares) más de lo que puede pagar. Y, finalmente, de todos modos, habría que pagar la factura, no en la forma de una alta inflación crónica, sino como un default de la deuda y todos los costos que eso conlleva. Mientras tanto, los responsables de formular la política monetaria tendrían que manejar las complejidades de la transición al nuevo sistema monetario, mientras aseguran a los acreedores existentes, incluido el Fondo Monetario Internacional, que el nuevo líder y su equipo saben lo que hacen. El economista Robin Brooks ratificó su crítica a la dolarización de Milei y se sumó al debate Los Gobiernos que pueden administrar los impuestos y el gasto de manera responsable no necesitan dolarizar sus economías. Aquellos que no puedan, seguirán arruinando las cosas con o sin su propio banco central. La pregunta que hay que hacerle a Milei y a sus rivales no es si la dolarización tiene sentido para el país, sino si se puede confiar en que aportarán un juicio económico sólido a la tarea que tienen entre manos. Si la reacción de los mercados financieros a la victoria de Milei en las primarias sirve de guía, la respuesta para él es un rotundo no. Traducido por Paulina Steffens. También te puede interesar Massa rumbo a Estados Unidos para negociar con el FMI y desmarcarse la “dolarización Milei” Confirmaron el romance de Fátima Florez y Javier Milei y en redes aparecieron desopilantes memes Patricia Bullrich: “No voy a hablar más de Milei” En esta Nota Milei Dolarización Elecciones")
                .releaseDate(date).build();
    }
}
