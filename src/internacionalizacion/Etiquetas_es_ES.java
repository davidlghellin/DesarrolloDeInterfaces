package internacionalizacion;

import java.util.ListResourceBundle;

public class Etiquetas_es_ES extends ListResourceBundle
{

	@Override
	public Object[][] getContents()
	{
		return contenido;
	}

	private Object[][]	contenido	= 
	{ 
			//titulo
			{ "etiqueta_titulo", "Mi editor de fuentes" },
			//salir
			{ "etiqueta_salir", "¿Desea salir?" },
			//barra
			{ "etiqueta_boton_abrir", "Abrir" },
			{ "etiqueta_boton_cerrar", "Cerrar" },
			{ "etiqueta_tamaño", "Tamaño" } ,
			{ "etiqueta_fuentes", "Fuentes" } ,
			{ "etiqueta_boton_color", "ColorFuente" },
			{ "etiqueta_boton_salir", "Salir" },
			// menu
			{ "etiqueta_boton_abrir_serializado", "Abrir serializado" } ,
			{ "etiqueta_boton_guardar_serializado", "Guardar serializado" } ,
			{ "etiqueta_menu_archivo", "Archivo" } ,
			{ "etiqueta_boton_guardar", "Guardar" } ,
			{ "etiqueta_boton_guardar_como", "Guardar como" } ,
			{ "etiqueta_menu_fuente", "Fuente" } ,
			{ "etiqueta_menu_estilo", "Estilo" } ,
			{ "etiqueta_menu_parrafo", "Parrafo" } ,
			{ "etiqueta_menu_parrafo_izquierda", "Izquierda" } ,
			{ "etiqueta_menu_parrafo_derecha", "Derecha" } ,
			{ "etiqueta_menu_parrafo_justificado", "Justificado" } ,
			{ "etiqueta_menu_parrafo_centrar", "Centrar" } ,
			{ "etiqueta_menu_tamaño", "Tamaño" } ,
			{ "etiqueta_menu_informacion", "Información" } ,
			{ "etiqueta_menu_informacion_nombre", "Nombre" } ,
			{ "etiqueta_menu_informacion_version", "Versión" },
			{ "info_barra_inferior_caracteres", "Número de caracteres: " },
			{ "info_barra_inferior_cursor", "\n Cursor en: " },
			
			//mejoras
			{ "etiqueta_boton_copiar", "Copiar" } ,
			{ "etiqueta_boton_pegar", "Pegar" } ,
			{ "etiqueta_boton_insertar_imagen", "Insertar imágen" } ,
			{ "etiqueta_boton_abrir_como", "Abrir como" } ,
	};

}
