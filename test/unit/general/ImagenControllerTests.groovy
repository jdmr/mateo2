package general



import org.junit.*
import grails.test.mixin.*


@TestFor(ImagenController)
@Mock(Imagen)
class ImagenControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/imagen/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.imagenInstanceList.size() == 0
        assert model.imagenInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.imagenInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.imagenInstance != null
        assert view == '/imagen/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/imagen/show/1'
        assert controller.flash.message != null
        assert Imagen.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/imagen/list'


        def imagen = new Imagen()

        // TODO: populate domain properties

        assert imagen.save() != null

        params.id = imagen.id

        def model = controller.show()

        assert model.imagenInstance == imagen
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/imagen/list'


        def imagen = new Imagen()

        // TODO: populate valid domain properties

        assert imagen.save() != null

        params.id = imagen.id

        def model = controller.edit()

        assert model.imagenInstance == imagen
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/imagen/list'

        response.reset()


        def imagen = new Imagen()

        // TODO: populate valid domain properties

        assert imagen.save() != null

        // test invalid parameters in update
        params.id = imagen.id

        controller.update()

        assert view == "/imagen/edit"
        assert model.imagenInstance != null

        imagen.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/imagen/show/$imagen.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/imagen/list'

        response.reset()

        def imagen = new Imagen()

        // TODO: populate valid domain properties
        assert imagen.save() != null
        assert Imagen.count() == 1

        params.id = imagen.id

        controller.delete()

        assert Imagen.count() == 0
        assert Imagen.get(imagen.id) == null
        assert response.redirectedUrl == '/imagen/list'


    }


}