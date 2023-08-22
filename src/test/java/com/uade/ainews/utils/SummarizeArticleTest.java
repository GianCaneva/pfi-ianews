package com.uade.ainews.utils;

import static org.junit.jupiter.api.Assertions.*;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class SummarizeArticleTest extends TestCase {
    @Test
    public void testSumUp() {
        String article1 = "Después de las PASO y la posterior devaluación anunciada por el Gobierno, los precios de algunos productos básicos subieron entre el 10% y el 30%. En el caso del pan, algunas cámaras afirmaron que comenzó a venderse a $ 950 por kilo, y la carne vacuna sigue liderando los aumentos de agosto, con incrementos de hasta el 30% en los últimos cinco días. Economistas y representantes de diferentes sectores consultados por PERFIL aseguraron que es posible que los precios sigan incrementándose la semana que viene. Tras las elecciones primarias, en el mismo supermercado y de la misma marca, el litro de leche aumentó casi 6% con respecto al día anterior a las PASO. Sin embargo, en pequeños comercios y a partir del lunes pasado, los almacenes fueron notificados de subas de hasta el 25% en algunos productos. “El lunes no nos podíamos comunicar con las empresas, pero el martes comenzamos con notificaciones de un 25% de aumento”, aseguró a PERFIL Fernando Savore, presidente de la Federación de Almaceneros de la provincia de Buenos Aires (FABA). Los principales incrementos se dieron en fideos, galletitas, yerba y artículos de higiene personal. “A los lácteos le sacaron la bonificación”, agregó el comerciante. En cuanto a la carne, y después de las PASO, el mismo kilo de asado, que el viernes previo a las elecciones costaba alrededor de $ 2.300, escaló a $ 3.200. “Las carnicerías van aumentando de a poco, pero veremos qué pasa la semana que viene, a partir del martes”, señaló a PERFIL Sergio Pedace, vicepresidente de la Cámara Argentina de Matarifes y Abastecedores (Camya). Según informaron desde el sector, el aumento que se aplicó esta semana ronda el 30%. En declaraciones radiales Miguel Schiariti, presidente de la Cámara de la Industria y Comercio de las Carnes (Ci-ccra), aseguró que estas subas no serán las únicas. Debido a la sequía “va a haber una caída en la oferta de alrededor del 20%, y esto va a generar un nuevo aumento de precios”, ya que “la sequía hizo que los servicios fueran muy malos”. La situación podría impactar también en la oferta del año que viene, explicó. La carne de cerdo también subió y seguirá subiendo. “El promedio de incremento esta semana fue de entre 10 y 15%”, afirmó a este medio Juan Uccelli, consultor del sector porcino. “Ya se fijó el precio para la semana del cerdo en pie que es del 22%, así que seguramente habrá un 10 o un 15% más en las carnicerías, pero seguimos estando muy lejos de la carne vacuna”, aseguró. El desconcierto fue predominante durante toda la semana. Un caso particular es el del pan, que según algunas cámaras aumentará entre un 10% y un 15%. Según le comentó a PERFIL Emilio Majori, titular del Centro Industrial de Panaderos de La Matanza, desde el viernes ya empezó a venderse a $ 950 el kilo, y a dos mil pesos la docena de facturas. Para iniciar la semana, el sector tuvo subas importantes: “el azúcar subió el 30% y las materias grasas entre el 35% y el 40%”, detalló Majori. Por su parte José Álvarez, presidente de la Cámara Industrial de Panaderos de Buenos Aires, aseguró a este medio que el precio del pan no debería aumentar. “Hemos sufrido un aumento en las materias grasas, dulce de leche y azúcar. El azúcar aumentó casi 50% y no sabemos por qué”, explicó. Sin embargo, según afirma, ninguno de estos ingredientes se usa para producir pan. El empresario relacionó la situación de los costos con los alquileres. Por eso, sostuvo “nadie puede decir lo que debe salir un kilo de pan”. Lo cierto es que en algunas panaderías empezaron a verse los incrementos, pero nadie se anima a vaticinar lo que puede pasar la semana que viene. “La incertidumbre en economía se traduce en riesgo y esto a su vez, se refleja en los precios”, explicó a PERFIL Lautaro Moschet, economista de la Fundación Libertad y Progreso. Por lo tanto, “es posible que en lo que reste del mes sigamos viendo ajustes de precios”, comentó. Para Moschet, “es esperable que una devaluación pronunciada como la que se decidió en estos días provoque una aceleración”. Las consultoras volvieron a corregir las previsiones de inflación para agosto. Según la Fundación Libertad y Progreso, podría ser de dos dígitos y superar el 10%. “Aún sin el salto en el tipo de cambio oficial, veníamos notando el fuerte aumento de precios, con lo cual la situación es alarmante y seguramente terminemos con una inflación mensual récord en los últimos 21 años”, sintetizó el economista. Hasta julio, según el Indec, una familia necesitó $ 248.962 para no ser considerada pobre. El impacto de la devaluación todavía no entraba en los cálculos que dio a conocer la institución esta semana. 10,7% en las últimas cuatro semanas R.P. Según un estudio privado la inflación en alimentos en la tercera semana de agosto fue del 4,1% y en las últimas cuatro avanzó 10,7%. El alza semanal estuvo liderada por bebidas e infusiones (8,6%), azúcar (8,1%), aceites (6,1%), frutas (5%) y verduras (4%). Algo menores fueron los aumentos en carne (3,2%), panificados (3,2%) y lácteos (2,9%). El informe corresponde a un trabajo de la consultora LCG que evalúa la evolución de los precios todas las semanas con corte los miércoles. Se estudia el comportamiento de 8 mil productos de cinco supermercados. Dada la característica de la muestra, el impacto del aumento de la carne que llegó a las carnicerías en las últimas horas se verá reflejado en los trabajos de las siguientes semanas. Cuando se observa el comportamiento de las cuatro últimas semanas a la tercera de agosto, los incrementos más importantes son panificados (20,5%), verduras (4,1%), bebidas (8,8%), azúcar (7%), y carnes (5,8%). Más atrás están lácteos (4,3%), aceites (4,3%) y frutas (1,2%).";
        String article2 = "Después de los aumentos de combustible del 12,5% en promedio, que aplicaron las petroleras esta semana, el Gobierno confirmó que las tarifas eléctricas volverán a subir. “Hay un componente atado al dólar, porque los contratos son a mediano y largo plazo y se busca una medida de actualización para mantener una remuneración y un contrato en dólares, pero una devaluación del 20% no significa un aumento del 20% en el costo de generación”, indicó la secretaria de Energía, Flavia Royón en declaraciones radiales. Las tarifas ya habían subido en junio y este mes, y muchos hogares residenciales, a los que les está llegando ahora las facturas, recibieron aumentos de más de 200% porque se conjugó la eliminación de los subsidios y el aumento de la tarifa eléctrica en sí misma. Ese componente tarifario puro es que se ajustará de nuevo por la devaluación del tipo de cambio oficial que ocurrió el lunes. Aún se desconoce de cuánto será efectivamente la nueva suba. A los incrementos superiores al 400% interanuales que se venían aplicando se sumarán nuevas subas derivadas de la devaluación. La devaluación también impactó fuerte en los precios de combustibles, cuyos acuerdos de precios vencieron el 15 de agosto. Pero, tras el incremento y el congelamiento acordado entre petroleras y Gobierno hasta el 31 de octubre, operadores, dueños de estaciones de servicio y representantes del sector, advirtieron a PERFIL que la medida puede generar desabastecimiento e incluso una reducción obligada de costos, con lo que no se descartan cierres o despidos. “Para el sector de las estaciones de servicio es un nuevo mazazo que atenta directamente contra nuestra rentabilidad”, aseguró a este medio Carlos Gold, secretario de Relaciones Institucionales de la Confederación de Entidades de Comercio de Hidrocarburos y Afines de la República Argentina (Cecha). Gold explicó que, por lo general, “cuando sucede esto de que los precios van por debajo de lo que debería estar en pizarra, las petroleras nos imponen cupos, y entonces aparece el desabastecimiento”. Si las compañías petroleras empiezan a entregar menos combustible porque “no le dan los números” se produce “una distorsión general en el mercado”, agregó el empresario. Después de la devaluación anunciada por el Gobierno el lunes, las petroleras Raizen (Shell), Axion y Puma aumentaron los precios del combustible. La última en acoplarse al incremento fue YPF, que había descartado hacerlo unas horas antes de tomar la decisión. Luego de conocerse el incremento el Ministerio de Economía anunció un acuerdo para congelar los precios hasta fines de octubre. “En el caso de YPF hubo una enorme transferencia de demanda por la diferencia de precios, lo que provocó que las estaciones quebráramos stock y tuviésemos tanques vacíos al momento del aumento”, comentó a este medio Martín Chada, operador de la empresa. “Esto significa que tenemos que reponer combustible a un precio nuevo habiendo vendido a precio anterior, con un traslado gigante de liquidez de las estaciones a las petroleras”, detalló. “Para graficarlo, el aumento de combustible y su congelamiento hasta octubre no alcanza a cubrir el 20% de la paritaria solicitada para el mismo período. Sin hablar de costos operativos, en su mayoría dolarizados, que tiene una estación”, afirmó Chada. Tanto Gold como Chada coincidieron en que este modelo de congelamiento y control tiene antecedentes, pero que nunca dio buenos resultados. “Este modelo ya existió, y entre 2005 y 2015 dejó 2.500 estaciones de servicio menos”, opinó en este sentido Chada. “Si bien el último eslabón de la cadena que se corta son los empleados, no descarto que empiecen a aparecer despidos”, agregó el representante de Cecha. “Medidas de esta naturaleza son las últimas que se desean, pero es la situación en la que estamos inmersos”, explicó. “Si el esfuerzo lo vamos a hacer nosotros, que nos den alguna herramienta que nos permita paliar el déficit, subsidios para pagar el reajuste salarial que tenemos que hacer ahora o algún beneficio impositivo”, pidió Gold. Por ahora, y después del convenio, el Gobierno informó que habrá beneficios fiscales para las petroleras, que pueden ser suspendidos en caso de incumplimiento. Los costos operativos de los que hablan los estacioneros no fueron contemplados por el acuerdo, aún después de la devaluación del 22% que se produjo después de las elecciones primarias. Desde la Cecha aseguraron a PERFIL que la semana que viene se reunirán para analizar la situación de la crisis. También te puede interesar La Aduana suspendió un frigorífico por subfacturación Lousteau aguarda un gesto del PRO Tras un reclamo de la Justicia, el Gobierno porteño no usará la Boleta Electrónica en octubre LLA promete más ajuste que el pedido por el FMI En esta Nota";
        StringBuilder finalArticle = new StringBuilder();
        finalArticle.append(article1).append(" ").append(article2);
        String response = SummarizeArticle.sumUp(String.valueOf(finalArticle));

    }
}