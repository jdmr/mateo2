package inventario



import org.junit.*
import grails.test.mixin.*


@TestFor(AlmacenController)
@Mock(Almacen)
class AlmacenControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/almacen/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.almacenInstanceList.size() == 0
        assert model.almacenInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.almacenInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.almacenInstance != null
        assert view == '/almacen/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/almacen/show/1'
        assert controller.flash.message != null
        assert Almacen.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/almacen/list'


        def almacen = new Almacen()

        // TODO: populate domain properties

        assert almacen.save() != null

        params.id = almacen.id

        def model = controller.show()

        assert model.almacenInstance == almacen
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/almacen/list'


        def almacen = new Almacen()

        // TODO: populate valid domain properties

        assert almacen.save() != null

        params.id = almacen.id

        def model = controller.edit()

        assert model.almacenInstance == almacen
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/almacen/list'

        response.reset()


        def almacen = new Almacen()

        // TODO: populate valid domain properties

        assert almacen.save() != null

        // test invalid parameters in update
        params.id = almacen.id

        controller.update()

        assert view == "/almacen/edit"
        assert model.almacenInstance != null

        almacen.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/almacen/show/$almacen.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/almacen/list'

        response.reset()

        def almacen = new Almacen()

        // TODO: populate valid domain properties
        assert almacen.save() != null
        assert Almacen.count() == 1

        params.id = almacen.id

        controller.delete()

        assert Almacen.count() == 0
        assert Almacen.get(almacen.id) == null
        assert response.redirectedUrl == '/almacen/list'


    }


}