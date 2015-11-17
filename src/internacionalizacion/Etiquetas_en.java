package internacionalizacion;

import java.util.ListResourceBundle;

public class Etiquetas_en extends ListResourceBundle
{

	@Override
	public Object[][] getContents()
	{
		return contenido;
	}

	private Object[][]	contenido	= 
	{ 
			//titulo
			{ "etiqueta_titulo", "My editor sources" },
			//salir
			{ "etiqueta_salir", "Do you want exit?" },
			//barra
			{ "etiqueta_boton_abrir", "Open" },
			{ "etiqueta_boton_cerrar", "Close" },
			{ "etiqueta_tamaño", "Size" } ,
			{ "etiqueta_fuentes", "Sources" } ,
			{ "etiqueta_boton_color", "ColorSource" } ,
			{ "etiqueta_boton_salir", "Exit" },
			// menu
			{ "etiqueta_boton_abrir_serializado", "Open serialized" } ,
			{ "etiqueta_boton_guardar_serializado", "Save serialized" } ,
			{ "etiqueta_menu_archivo", "Archive" } ,
			{ "etiqueta_boton_guardar", "Save" } ,
			{ "etiqueta_boton_guardar_como", "Save as" } ,
			{ "etiqueta_menu_fuente", "Sources" } ,
			{ "etiqueta_menu_estilo", "Style" } ,
			{ "etiqueta_menu_parrafo", "Paragraph" } ,
			{ "etiqueta_menu_parrafo_izquierda", "Left" } ,
			{ "etiqueta_menu_parrafo_derecha", "Right" } ,
			{ "etiqueta_menu_parrafo_justificado", "Justified" } ,
			{ "etiqueta_menu_parrafo_centrar", "Center" } ,
			{ "etiqueta_menu_tamaño", "Size" } ,
			{ "etiqueta_menu_informacion", "Information" } ,
			{ "etiqueta_menu_informacion_nombre", "Name" } ,
			{ "etiqueta_menu_informacion_version", "Version" },
			{ "info_barra_inferior_caracteres", "Number of characters: " },
			{ "info_barra_inferior_cursor", "\n Cursor in: " },
			
			//mejoras
			{ "etiqueta_boton_copiar", "Copy" } ,
			{ "etiqueta_boton_pegar", "Paste" } ,
			{ "etiqueta_boton_insertar_imagen", "Insert image" } ,
			{ "etiqueta_boton_abrir_como", "Open as" } ,
	};
}
